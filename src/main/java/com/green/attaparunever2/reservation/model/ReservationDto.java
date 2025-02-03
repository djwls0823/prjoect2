package com.green.attaparunever2.reservation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReservationDto {
    private long reservationId;
    private long orderId;
    private long restaurantId;
    private String restaurantName;
    private long userId;
    private String userName;
    private int reservationYn;
    private int reservationStatus;
    private LocalDateTime reservationTime;
    private int reservationPeopleCount;
    private String reservationCancelReason;
    private String userPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
