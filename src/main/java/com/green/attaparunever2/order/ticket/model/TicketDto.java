package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(title = "식권 정보")
public class TicketDto {
    @Schema(title = "식권 PK")
    private long ticketId;
    @Schema(title = "주문 PK")
    private long orderId;
    @Schema(title = "만료시간")
    private String expiredDate;
    @Schema(title = "식권 사용 여부", description = "0: 미사용, 1: 사용")
    private int ticketStatus;
    @Schema(title = "식권 사용 일자")
    private String useDate;
    @Schema(title = "식권 생성일")
    private String createdAt;
    @Schema(title = "식권 금액")
    private int point;
}
