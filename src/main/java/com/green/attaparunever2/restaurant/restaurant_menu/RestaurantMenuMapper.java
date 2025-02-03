package com.green.attaparunever2.restaurant.restaurant_menu;

import com.green.attaparunever2.restaurant.restaurant_menu.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMenuMapper {
    int insMenu(InsMenuReq p);
    List<SelMenuRes> selMenu(SelMenuReq p);
    int delMenu(DelMenuReq p);
    int updMenu(UpdMenuReq p);
    int postCategory(PostCategoryReq p);
    int updCategory(UpdCategoryReq p);
    int delCategory(DelCategoryReq p);
    List<MenuSelCateList> selMenuCategoryList(long restaurantId);
    List<MenuSelList> selMenuList(long categoryId);
}
