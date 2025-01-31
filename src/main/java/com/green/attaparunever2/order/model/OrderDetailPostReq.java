package com.green.attaparunever2.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "주문 상세")
public class OrderDetailPostReq {
    @JsonIgnore
    private long orderDetailId;

    @Schema(title = "주문 PK")
    private long orderId;

    @Schema(title = "메뉴 PK")
    private long menuId;

    @Schema(title = "메뉴 수량")
    private int menuCount;

    @Schema(title = "메뉴 주문 가격")
    private int price;
}
