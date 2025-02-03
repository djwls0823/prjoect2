package com.green.attaparunever2.restaurant.restaurant_pic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantPicAroundSel {
    @JsonIgnore
    @Schema(title = "식당 PK", example = "0")
    private long restaurantId;
    @Schema(title = "식당 사진")
    private String filePath;
}
