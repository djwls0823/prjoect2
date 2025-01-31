package com.green.attaparunever2.restaurant.restaurant_menu;


import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.restaurant.restaurant_menu.model.DelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
