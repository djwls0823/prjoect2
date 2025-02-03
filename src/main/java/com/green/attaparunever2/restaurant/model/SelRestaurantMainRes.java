package com.green.attaparunever2.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.attaparunever2.restaurant.restaurant_pic.model.RestaurantPicAroundSel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelRestaurantMainRes {
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = "식당 주소")
    private String restaurantAddress;
    @Schema(title = "식당 평균 식사 시간")
    private double avgRestaurant;
    @Schema(title = "식당 사진 리스트")
    private RestaurantPicAroundSel restaurantAroundPicList;
}
