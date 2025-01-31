package com.green.attaparunever2.reservation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(title = "예약 요청 등록")
public class ReservationPostReq {
    @Schema(title = "주문 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;
    @Schema(title = "예약 인원 수")
    private int reservationPeopleCount;
    @Schema(title = "사용자 연락처")
    private String userPhone;
    @Schema(title = "예약 시간")
    private String reservationTime;
    @Schema(title = "식당 PK(예약요청 알림 전송을 위해 필요)", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;
}