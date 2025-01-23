package com.green.attaparunever2.restaurant.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelRestaurantAroundRes {
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = "식당 주소")
    private String restaurantAddress;
    @Schema(title = "식당 전화번호")
    private String restaurantNumber;
}
