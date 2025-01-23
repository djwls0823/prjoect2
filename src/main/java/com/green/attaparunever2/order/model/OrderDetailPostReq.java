package com.green.attaparunever2.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "주문 상세 정보 등록")
public class OrderDetailPostReq {
    @JsonIgnore
    private long orderDetailId;

    private long orderId;
    private long menuId;
    private int menuCount;
    private int price;
}
