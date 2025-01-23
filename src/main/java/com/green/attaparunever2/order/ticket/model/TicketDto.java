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
    private long ticketId;
    private long orderId;
    private String expiredDate;
    private int ticketStatus;
    private String useDate;
    private String createdAt;
    private int point;
    private String restaurantName;
    private int totalAmount;
    private int personCount;
    private String reservationTime;
}
