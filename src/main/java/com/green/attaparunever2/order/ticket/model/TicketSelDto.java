package com.green.attaparunever2.order.ticket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TicketSelDto {
    private long ticketId;
    private long orderId;
    private LocalDateTime expiredDate;
    private int ticketStatus;
    private LocalDateTime useDate;
    private LocalDateTime createdAt;
}
