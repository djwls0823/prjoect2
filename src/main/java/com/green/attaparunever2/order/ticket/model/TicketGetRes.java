package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "식권 조회 응답")
public class TicketGetRes {
    @Schema(title = "티켓 목록")
    private List<TicketDto> ticketList;
}
