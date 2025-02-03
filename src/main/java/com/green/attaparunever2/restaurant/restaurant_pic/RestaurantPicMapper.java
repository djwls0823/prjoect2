package com.green.attaparunever2.restaurant.restaurant_pic;

import com.green.attaparunever2.restaurant.model.RestaurantPicDto;
import com.green.attaparunever2.restaurant.model.SelRestaurantRes;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicAroundSel;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicSel;
import com.green.attaparunever2.restaurant.restaurant_pic.model.UpdRestaurantMenuPicReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestaurantPicMapper {
    int insRestaurantPic(RestaurantPicDto p);
    int updRestaurantMenuPic(UpdRestaurantMenuPicReq p);
    int deleteRestaurantPics(@Param("restaurantId") long restaurantId, @Param("picId") List<Long> picId);
    String getFilePathByPicId(long picId);
    RestaurantPicSel selRestaurantPic(long restaurantId);
    List<RestaurantPicAroundSel> selRestaurantAroundPic(long restaurantId);
    RestaurantPicAroundSel selRestaurantMainPic(long restaurantId);
}