package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.ticket.model.PaymentRestaurantGetReq;
import com.green.attaparunever2.order.ticket.model.PaymentRestaurantGetRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order/payment-restaurant")
@Tag(name = "매장의 매출 내역")
public class PaymentRestaurantController {
    private final PaymentRestaurantService service;

    @GetMapping
    @Operation(summary = "매장의 매출 내역 리스트")
    public ResultResponse<PaymentRestaurantGetRes> getPaymentRestaurant(@ParameterObject @ModelAttribute PaymentRestaurantGetReq req) {
        PaymentRestaurantGetRes res = service.getPaymentRestaurant(req);
        return ResultResponse.<PaymentRestaurantGetRes>builder()
                .statusCode("200")
                .resultMsg("매장 매출 내역 조회 완료")
                .resultData(res)
                .build();
    }
}
