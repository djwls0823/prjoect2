package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "사용자 결제 내역 조회 응답")
public class PaymentUserGetRes {

    @Schema(title = "결제 내역 리스트")
    private List<PaymentUserDto> paymentList;

}
