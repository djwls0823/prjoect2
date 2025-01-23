package com.green.attaparunever2.restaurant;

import com.green.attaparunever2.restaurant.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    int insRestaurant(InsRestaurantReq p);
    SelRestaurantRes selRestaurantOne(SelRestaurantReq p);
    List<SelRestaurantAroundRes> selRestaurantAround(SelRestaurantAroundReq p);

    int insHoliday(InsHolidayReq p);
    List<SelHolidayRes> selHolidays(SelHolidayReq p);
}
