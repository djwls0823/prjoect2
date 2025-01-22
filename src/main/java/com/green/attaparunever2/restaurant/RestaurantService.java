package com.green.attaparunever2.restaurant;


import com.green.attaparunever2.restaurant.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public int postHoliday(InsHolidayReq p){
        int result = restaurantMapper.insHoliday(p);

        return result;
    }

    public List<SelHolidayRes> getHoliday(SelHolidayReq p){
        List<SelHolidayRes> res = restaurantMapper.selHolidays(p);

        return res;
    }

}
