package com.green.attaparunever2.restaurant;

import com.green.attaparunever2.restaurant.model.RestaurantPicDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantPicMapper {
    int insRestaurantPic(RestaurantPicDto p);
}