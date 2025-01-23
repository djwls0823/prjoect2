package com.green.attaparunever2.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantPicDto {
    private long restaurantId;
    private List<String> filePath;
}
