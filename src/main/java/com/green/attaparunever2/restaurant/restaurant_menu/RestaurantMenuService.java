package com.green.attaparunever2.restaurant.restaurant_menu;


import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.restaurant.restaurant_menu.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantMenuService {
    private final RestaurantMenuMapper restaurantMenuMapper;
    private final MyFileUtils myFileUtils;

    public int postMenu(MultipartFile pic, InsMenuReq p){ // 메뉴 사진 등록 해야함
        String savedPicName = myFileUtils.makeRandomFileName(pic);
        p.setMenuPic(savedPicName);
        int result = restaurantMenuMapper.insMenu(p);

        long menuId = p.getMenuId();

        String middlePath = String.format("menu/%d", menuId);
        myFileUtils.makeFolders(middlePath);
        String filePath = String.format("%s/%s", middlePath, savedPicName);
        try {
            myFileUtils.transferTo(pic, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<SelMenuRes> getMenu(SelMenuReq p){
        List<SelMenuRes> res = restaurantMenuMapper.selMenu(p);

        return res;
    }

    public int delMenu(DelMenuReq p){
        return restaurantMenuMapper.delMenu(p);
    }

    @Transactional
    public String updRestaurantMenu(MultipartFile pic, UpdMenuReq p) {
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
        int result = restaurantMenuMapper.updMenu(p);
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
