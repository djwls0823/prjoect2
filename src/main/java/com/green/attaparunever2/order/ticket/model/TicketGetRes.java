package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "식권 조회 응답")
public class TicketGetRes {
    @Schema(title = "식권 정보")
    private TicketDto ticket;
}

