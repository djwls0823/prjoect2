package com.green.attaparunever2.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicAroundSel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelRestaurantAroundRes {
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = "식당 주소")
    private String restaurantAddress;
    @Schema(title = "식당 평균 식사 시간")
    private double avgRestaurant;
    @Schema(title = "위도")
    private double lat;
    @Schema(title = "경도")
    private double lng;
    @Schema(title = "식당 사진 리스트")
    private List<RestaurantPicAroundSel> restaurantArroundPicList;
}
