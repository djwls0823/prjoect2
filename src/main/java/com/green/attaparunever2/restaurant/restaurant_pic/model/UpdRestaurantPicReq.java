package com.green.attaparunever2.restaurant.restaurant_pic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdRestaurantPicReq {
    private long restaurantId;
    private List<String> filePath;
    private List<String> picName;
    private long picId;
}
