package com.green.attaparunever2.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Schema(title = "예약 요청 등록")
public class ReservationPostReq {
    @JsonIgnore
    private long reservationId; // 예약 후 변경 없을 시 10분 뒤에 자동 취소 처리를 위해 필요.
    @JsonIgnore
    private long orderId;
    @Schema(title = "식당 PK(예약요청 알림 전송을 위해 필요)", requiredMode = Schema.RequiredMode.REQUIRED)
    private long restaurantId;
    @Schema(title = "유저 PK(다른 예약 여부 확인을 위해 필요)", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "예약 시간")
    private String reservationTime;
    @Schema(title = "예약 인원 수")
    private int reservationPeopleCount;
    @Schema(title = "사용자 연락처")
    private String userPhone;
    @Schema(title = "주문 메뉴 리스트")
    private List<ReservationMenuDto> menuList;

}