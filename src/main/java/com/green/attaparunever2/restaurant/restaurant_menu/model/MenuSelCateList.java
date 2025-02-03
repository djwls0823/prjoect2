package com.green.attaparunever2.restaurant.restaurant_menu.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuSelCateList {
    @Schema(title = "메뉴 카테고리 PK")
    private long categoryId;
    @Schema(title = "메뉴 카테고리 이름")
    private String categoryName;
    @Schema(title = "식당 메뉴 리스트")
    private List<MenuSelList> menuList;
}
