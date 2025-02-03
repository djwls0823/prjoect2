package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantPicService {
    private final RestaurantPicMapper restaurantPicMapper;
    private final MyFileUtils myFileUtils;

    public String updRestaurantMenuPic(MultipartFile pic, UpdRestaurantMenuPicReq p) {
        // 저장할 파일명 생성
        String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);

        // 폴더 만들기
        String folderPath = String.format("menu/%d", p.getMenuId());
        myFileUtils.makeFolders(folderPath);

        // 기존 파일 삭제
        String deletePath = String.format("%s/menu/%d", myFileUtils.getUploadPath(), p.getMenuId());
        myFileUtils.deleteFolder(deletePath, false);

        // DB에 튜플 수정
        p.setPicName(savedPicName); // 파일 이름을 설정
        int result = restaurantPicMapper.updRestaurantMenuPic(p);
        if (result == 0) {
            throw new CustomException("메뉴 수정 실패", HttpStatus.BAD_REQUEST);
        }

        // 파일 이동
        String filePath = String.format("menu/%d/%s", p.getMenuId(), savedPicName);
        try {
            myFileUtils.transferTo(pic, filePath); // MultipartFile을 파일로 저장
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 이동 실패");
        }
        return savedPicName;
    }

    @Transactional
    public int delRestaurantPic(long restaurantId, List<Long> picIds) {
        // 1. 삭제할 파일 경로를 직접 가져온다 (이미 알고 있으므로 조회할 필요 없음)
        // 해당 경로는 사진 등록할 때와 동일하게 설정됨
        String middlePath = String.format("restaurant/%d", restaurantId);

        // 실제 경로를 저장할 리스트
        List<String> picPaths = new ArrayList<>();

        for (Long picId : picIds) {
            // 각 picId에 대해 파일명을 만들거나, 저장된 파일명을 찾을 수 있다면 그대로 사용
            String savedPicName = restaurantPicMapper.getFilePathByPicId(picId);  // 예시로, picId에 대응되는 파일명을 구한다고 가정
            picPaths.add(String.format("%s/%s", middlePath, savedPicName));
        }

        if (picPaths.isEmpty()) {
            throw new CustomException("삭제할 사진을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        // 2. 파일 삭제
        String uploadPath = myFileUtils.getUploadPath();  // 실제 업로드 경로를 가져옵니다
        for (String picPath : picPaths) {
            String fullPath = String.format("%s/%s", uploadPath, picPath);
            try {
                // 파일 삭제
                myFileUtils.deleteFile(fullPath);
            } catch (IOException e) {
                throw new CustomException("파일 삭제 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // 3. 데이터베이스에서 사진 정보 삭제
        int deleteResult = restaurantPicMapper.deleteRestaurantPics(restaurantId, picIds);
        if (deleteResult == 0) {
            throw new CustomException("데이터베이스에서 사진 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return deleteResult;
    }
}
