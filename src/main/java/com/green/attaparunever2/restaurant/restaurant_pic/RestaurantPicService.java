package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
