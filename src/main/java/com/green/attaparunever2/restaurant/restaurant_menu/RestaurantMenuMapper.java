package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantMenuMapper {
    int insMenu(InsMenuReq p);
}
