package com.green.attaparunever2.restaurant.model;


import com.green.attaparunever2.restaurant.restaurant_menu.model.MenuSelCateList;
import com.green.attaparunever2.restaurant.restaurant_menu.model.MenuSelList;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicSel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelRestaurantRes {
    @Schema(title = "관리자 PK")
    private long adminId;
    @Schema(title = "카테고리 PK")
    private long categoryId;
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = "식당 주소")
    private String restaurantAddress;
    @Schema(title = "식당 번호")
    private String restaurantNumber;
    @Schema(title = "식당 사업자번호")
    private String businessNumber;
    @Schema(title = "식당 영업시간")
    private String operatingHours;
    @Schema(title = "식당 설명")
    private String restaurantDescription;
    @Schema(title = "식당 상태")
    private int status;
    @Schema(title = "식당 인원 수용량")
    private int maxCapacity;
    @Schema(title = "생성 시간")
    private String createdAt;
    @Schema(title = "식당 사진 리스트")
    private RestaurantPicSel restaurantPics;
    @Schema(title = "메뉴 카테고리 리스트")
    private List<MenuSelCateList> menuCateList;
}
