package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@Schema(title = "주문 조회 요청")
@ToString
public class OrderGetReq {
    private long orderId;

    @Schema(title = "주문 ID")
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
