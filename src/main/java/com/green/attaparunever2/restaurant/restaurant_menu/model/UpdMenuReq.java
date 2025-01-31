package com.green.attaparunever2.restaurant.restaurant_menu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Schema(title = "메뉴 수정 요청")
public class UpdMenuReq {
    @Schema(title = "메뉴 PK")
    private long menuId;
    @Schema(title = "카테고리 PK")
    private long categoryId;
    @Schema(title = "메뉴 이름")
    private String menuName;
    @Schema(title = "가격")
    private int price;
    @Schema(title = "메뉴 설명")
    private String details;
    @Schema(title = "주문 가능 상태", description = "true = 1, false = 0")
    private int available;
}
