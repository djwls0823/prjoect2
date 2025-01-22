package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.restaurant.restaurant_menu.model.InsMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuReq;
import com.green.attaparunever2.restaurant.restaurant_menu.model.SelMenuRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMenuMapper {
    int insMenu(InsMenuReq p);
    List<SelMenuRes> selMenu(SelMenuReq p);
}
