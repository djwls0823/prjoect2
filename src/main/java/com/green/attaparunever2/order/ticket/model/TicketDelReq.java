package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "식권 삭제 요청")
public class TicketDelReq {
    @Schema(title = "주문 PK")
    private long orderId;
    @Schema(title = "예약 생성일")
    private String createdAt;
}
