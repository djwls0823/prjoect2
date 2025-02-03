package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.common.MyFileUtils;
import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.restaurant.model.*;
import com.green.attaparunever2.restaurant.restaurant_menu.RestaurantMenuMapper;
import com.green.attaparunever2.restaurant.restaurant_menu.model.MenuSelCateList;
import com.green.attaparunever2.restaurant.restaurant_menu.model.MenuSelList;
import com.green.attaparunever2.restaurant.restaurant_pic.RestaurantPicMapper;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicAroundSel;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicSel;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantPicMapper restaurantPicMapper;
    private final RestaurantMenuMapper restaurantMenuMapper;
    private final MyFileUtils myFileUtils;

    @Transactional
    public InsRestaurantRes postRestaurant(List<MultipartFile> filePath, InsRestaurantReq p){
        int result = restaurantMapper.insRestaurant(p);
        if (result == 0) {
            throw new CustomException("식당 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        //파일 등록
        long restaurantId = p.getRestaurantId();

        String middlePath = String.format("restaurant/%d", restaurantId);
        myFileUtils.makeFolders(middlePath);

        List<String> picNameList = new ArrayList<>(filePath.size());
        for(MultipartFile pic : filePath) {
            //각 파일 랜덤파일명 만들기
            String savedPicName = myFileUtils.makeRandomFileName(pic);
            picNameList.add(savedPicName);
            String picPath = String.format("%s/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, picPath);
            } catch (IOException e) {
                //폴더 삭제 처리
                String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                myFileUtils.deleteFolder(delFolderPath, true);
                throw new CustomException("식당 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        }
        RestaurantPicDto restaurantPicDto = new RestaurantPicDto();
        restaurantPicDto.setRestaurantId(restaurantId);
        restaurantPicDto.setFilePath(picNameList);
        int resultPics = restaurantPicMapper.insRestaurantPic(restaurantPicDto);

        return InsRestaurantRes.builder()
                .restaurantId(restaurantId)
                .filePath(picNameList)
                .build();
    }

    public SelRestaurantRes getRestaurant(SelRestaurantReq p){
        // 식당 정보 불러오기
        SelRestaurantRes res = restaurantMapper.selRestaurant(p);
        // 식당 사진 불러오기
        RestaurantPicSel restaurantPicSel = restaurantPicMapper.selRestaurantPic(p.getRestaurantId());
        res.setRestaurantPics(restaurantPicSel);
        // 식당 메뉴 카테고리 불러오기
        List<MenuSelCateList> menuSelCateList = restaurantMenuMapper.selMenuCategoryList(p.getRestaurantId());
        res.setMenuCateList(menuSelCateList);
        // 식당 메뉴 불러오기
        for (MenuSelCateList category : menuSelCateList) {
            // 4.1 각 카테고리 ID를 사용하여 해당 카테고리의 메뉴를 조회
            List<MenuSelList> menuList = restaurantMenuMapper.selMenuList(category.getCategoryId());

            // 4.2 카테고리 객체에 메뉴 리스트 설정
            category.setMenuList(menuList);
        }
        return res;
    }

    public List<SelRestaurantAroundRes> getRestaurantAround(SelRestaurantAroundReq p){
        double latitude = p.getUserLat(); // 위도
        double longitude = p.getUserLng(); // 경도

        // 반경 3km
        double radiusInKm = 3.0;

        // 위도 1도의 거리 (고정: 111km)
        double latitudeDegreeInKm = 111.0;
        double latitudeDiff = radiusInKm / latitudeDegreeInKm;

        // 경도 1도의 거리 (위도에 따라 다름)
        double longitudeDegreeInKm = 111.0 * Math.cos(Math.toRadians(latitude));
        double longitudeDiff = radiusInKm / longitudeDegreeInKm;

        // 위도와 경도의 범위 계산
        double minLatitude = latitude - latitudeDiff; // 가까운 위도
        double maxLatitude = latitude + latitudeDiff; // 먼 위도
        double minLongitude = longitude - longitudeDiff; // 가까운 경도
        double maxLongitude = longitude + longitudeDiff; // 먼 경도

        p.setSysMinLat(minLatitude);
        p.setSysMinLng(minLongitude);
        p.setSysMaxLat(maxLatitude);
        p.setSysMaxLng(maxLongitude);

        log.info("aiobhdfibhfdibhi {} asda {} asdasd", p.getSysMinLat(), p.getSysMinLng());
        log.info("asdasifjaisfjasi {} {}", p.getSysMaxLat(), p.getSysMaxLng());

        log.info("오더 필터 : {} 검색 필터 {}", p.getOrderFilter(), p.getSearchFilter());

        List<SelRestaurantAroundRes> list = restaurantMapper.selRestaurantAround(p);
        for (SelRestaurantAroundRes res : list) {
            // 각 식당에 대해 사진 리스트를 가져오기
            List<RestaurantPicAroundSel> picList = restaurantPicMapper.selRestaurantAroundPic(res.getRestaurantId());

            // 사진 리스트를 해당 식당 객체에 설정
            res.setRestaurantArroundPicList(picList);

            log.info("qwer : {} pic : {}", res.getRestaurantId(), res.getRestaurantArroundPicList());
        }

        // 3. 최종적으로 수정된 식당 목록 반환
        return list;
    }


    public int postHoliday(InsHolidayReq p){
        int result = restaurantMapper.insHoliday(p);

        return result;
    }

    public List<SelHolidayRes> getHoliday(SelHolidayReq p){
        List<SelHolidayRes> res = restaurantMapper.selHolidays(p);

        return res;
    }

    @Transactional
    public int patchRestaurant(UpdRestaurantReq req) {
        int result = restaurantMapper.updRestaurant(req);
        if (result == 0) {
            throw new CustomException("식당 수정에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    public int patchHoliday(UpdHolidayReq req) {
        int result = restaurantMapper.updHoliday(req);

        if (result == 0) {
            throw new CustomException("휴무일 수정에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    public List<SelRestaurantMainRes> getRestaurantMain(SelRestaurantMainReq p){
        // 식당 정보 불러오기
        List<SelRestaurantMainRes> res = restaurantMapper.selRestaurantMain(p);
        // 식당 사진 불러오기
        for (SelRestaurantMainRes item : res) {
            RestaurantPicAroundSel picList = restaurantPicMapper.selRestaurantMainPic(item.getRestaurantId());
            item.setRestaurantAroundPicList(picList);
        }
        return res;
    }
}