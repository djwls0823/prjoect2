package com.green.attaparunever2.order.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String menuNames;
    private String restaurantName;
    private int totalAmount;
    private int personCount;
    private String reservationTime;
}
