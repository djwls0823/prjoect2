package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "매장 매출 내역 조회 응답")
public class PaymentRestaurantGetRes {

    @Schema(title = "매출 내역 리스트")
    private List<PaymentRestaurantDto> paymentList;
}
