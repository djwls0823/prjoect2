package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@Schema(title = "사용자 결제 내역 조회 요청")
@ToString
public class PaymentUserGetReq {
    @Schema(title = "사용자 PK")
    private long userId;
}
