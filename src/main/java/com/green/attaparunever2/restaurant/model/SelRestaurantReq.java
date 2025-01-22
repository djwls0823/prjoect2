package com.green.attaparunever2.restaurant.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelRestaurantReq {
    @Schema(title = "식당 PK", example = "1")
    private long restaurantId;
}
