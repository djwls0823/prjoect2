package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "주문 등록")
public class OrderPostReq {
    private long userId;
    private long restaurantId;
}
