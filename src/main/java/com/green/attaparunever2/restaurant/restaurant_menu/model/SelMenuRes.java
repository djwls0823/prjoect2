package com.green.attaparunever2.restaurant.restaurant_menu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelMenuRes {
    @Schema(title = "메뉴 이름")
    private String menuName;
    @Schema(title = "가격")
    private int price;
    @Schema(title = "메뉴 설명")
    private String details;
    @Schema(title = "주문 가능 상태")
    private int available;
    @Schema(title = "메뉴 사진")
    private String menuPic;
}
