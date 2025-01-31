package com.green.attaparunever2.restaurant.restaurant_menu.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelMenuReq {
    @Schema(title = "메뉴 PK", example = "10")
    private long menuId;
}
