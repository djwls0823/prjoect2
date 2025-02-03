package com.green.attaparunever2.restaurant.restaurant_menu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuSelList {
    @Schema(title = "식당 메뉴 PK")
    private long menuId;
    @Schema(title = "식당 메뉴 이름")
    private String menuName;
    @Schema(title = "식당 메뉴 사진")
    private String menuPic;
    @Schema(title = "식당 메뉴 설명")
    private String details;
    @Schema(title = "식당 메뉴 가격")
    private int price;
    @Schema(title = "식당 메뉴 주문 가능 상태")
    private int available;
}
