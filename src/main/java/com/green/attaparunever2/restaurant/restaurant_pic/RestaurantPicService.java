package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantPicReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public List<String> updRestaurantPic(List<MultipartFile> pics, UpdRestaurantPicReq p) {
        List<String> savedPicNames = new ArrayList<>();

        // 폴더 만들기
        String folderPath = String.format("restaurant/%d", p.getRestaurantId());
        myFileUtils.makeFolders(folderPath);

        // 기존 파일 삭제 (필요에 따라)
        String deletePath = String.format("%s/restaurant/%d", myFileUtils.getUploadPath(), p.getRestaurantId());
        myFileUtils.deleteFolder(deletePath, false);

        // 각 사진 처리
        for (MultipartFile pic : pics) {
            // 저장할 파일명 생성
            String savedPicName = (pic != null ? myFileUtils.makeRandomFileName(pic) : null);
            savedPicNames.add(savedPicName); // 저장된 파일 이름을 리스트에 추가

            // 파일 이동
            String filePath = String.format("restaurant/%d/%s", p.getRestaurantId(), savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath); // MultipartFile을 파일로 저장
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 이동 실패");
            }
        }

        // DB에 모든 사진 정보 업데이트
        p.setPicName(savedPicNames); // 저장된 파일 이름을 요청 객체에 설정
        int result = restaurantPicMapper.updRestaurantPic(p);
        if (result == 0) {
            throw new CustomException("식당 사진 수정 실패", HttpStatus.BAD_REQUEST);
        }

        return savedPicNames; // 저장된 파일 이름 리스트 반환
    }
}
