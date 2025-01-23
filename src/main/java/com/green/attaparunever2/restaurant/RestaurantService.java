package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.restaurant.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantMapper restaurantMapper;

    public int postRestaurant(InsRestaurantReq p){
        int result = restaurantMapper.insRestaurant(p);

        return result;
    }

    public SelRestaurantRes getRestaurant(SelRestaurantReq p){
        SelRestaurantRes res = restaurantMapper.selRestaurantOne(p);
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

        p.setUserLat(minLatitude);
        p.setUserLng(minLongitude);
        p.setSysLat(maxLatitude);
        p.setSysLng(maxLongitude);

        log.info("aaaaaaaaaaaaaaa {} {}", p.getUserLat(), p.getUserLng());
        log.info("bbbbbbbbbbbbbbbbb {} {}", p.getSysLat(), p.getSysLng());
        List<SelRestaurantAroundRes> res = restaurantMapper.selRestaurantAround(p);

        return res;
    }


    public int postHoliday(InsHolidayReq p){
        int result = restaurantMapper.insHoliday(p);

        return result;
    }

    public List<SelHolidayRes> getHoliday(SelHolidayReq p){
        List<SelHolidayRes> res = restaurantMapper.selHolidays(p);

        return res;
    }

    public int patchRestaurant(UpdRestaurantReq req) {
        int result = restaurantMapper.updRestaurant(req);

        return result;
    }
}
