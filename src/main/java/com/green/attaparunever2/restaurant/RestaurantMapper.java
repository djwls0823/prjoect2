package com.green.attaparunever2.restaurant;

import com.green.attaparunever2.restaurant.model.InsRestaurantReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantMapper {
    int insRestaurant(InsRestaurantReq p);
}
