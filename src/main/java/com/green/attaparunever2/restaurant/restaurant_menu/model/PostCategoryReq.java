package com.green.attaparunever2.restaurant.restaurant_menu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryReq {
    private long restaurantId;
    private String categoryName;
}
