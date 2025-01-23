package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class InsRestaurantRes {
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "식당 사진 리스트")
    private List<String> filePath;
}
