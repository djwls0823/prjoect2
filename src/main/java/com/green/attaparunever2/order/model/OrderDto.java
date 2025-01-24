package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "주문 정보")
public class OrderDto {
    @Schema(title = "주문 ID")
    private long orderId;

    @Schema(title = "사용자 ID")
    private long userId;

    @Schema(title = "주문 상태")
    private String orderStatus;

    @Schema(title = "주문 날짜")
    private String orderDate;

    @Schema(title = "메뉴 ID")
    private long menuId;

    @Schema(title = "메뉴 이름")
    private String menuName;

    @Schema(title = "메뉴 수량")
    private int menuCount;

    @Schema(title = "메뉴 가격")
    private int menuPrice;

    @Schema(title = "총 가격")
    private int totalPrice;

    @Schema(title = "예약 ID")
    private long reservationId;

    @Schema(title = "예약 시간")
    private String reservationTime;

    @Schema(title = "예약 인원 수")
    private int reservationPeopleCount;

    @Schema(title = "예약 취소 사유")
    private String reservationCancelReason;

    @Schema(title = "사용자 전화번호")
    private String userPhone;

    @Schema(title = "예약 생성일")
    private String reservationCreatedAt;


}
