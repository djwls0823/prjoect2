package com.green.attaparunever2.restaurant.restaurant_menu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelMenuReq {
    @Schema(title = "카테고리 Pk", example = "1")
    private long categoryId;
}
