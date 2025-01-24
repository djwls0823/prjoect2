package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentUserDto {
    @Schema(title = "사용자 ID")
    private long userId;

    @Schema(title = "포인트")
    private int point;

    @Schema(title = "주문 ID")
    private long orderId;

    @Schema(title = "예약 상태")
    private int reservationStatus;

    @Schema(title = "결제 날짜")
    private String paymentDate;

    @Schema(title = "식권 ID")
    private long ticketId;

    @Schema(title = "식권 상태")
    private int ticketStatus;

    @Schema(title = "메뉴 ID")
    private long menuId;

    @Schema(title = "메뉴 수량")
    private int menuCount;

    @Schema(title = "가격")
    private int price;

    @Schema(title = "메뉴 이름")
    private String menuName;
}
