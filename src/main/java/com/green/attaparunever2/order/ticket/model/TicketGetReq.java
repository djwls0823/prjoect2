package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@Schema(title = "식권 조회 요청")
@ToString
public class TicketGetReq {
    private long orderId;
}
