package com.green.attaparunever2.restaurant.restaurant_menu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InsMenuReq {
    @Schema(title = "카테고리 PK", example = "1")
    private long categoryId;
    @Schema(title = "메뉴 이름", example = "김치찌개")
    private String menuName;
    @Schema(title = "메뉴 사진")
    private String menuPic;
    @Schema(title = "메뉴 가격", example = "8000")
    private int price;
    @Schema(title = "메뉴 설명", example = "김치로 만든 찌개")
    private String details;
    @Schema(title = "주문 가능 상태", example = "0")
    private int available;
}
