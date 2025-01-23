package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.ticket.model.PaymentUserGetReq;
import com.green.attaparunever2.order.ticket.model.PaymentUserGetRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order/payment-user")
@Tag(name = "유저의 결제 내역")
public class PaymentUserController {
    private final PaymentUserService service;

    @GetMapping
    @Operation(summary = "유저의 결제 내역 리스트")
    public ResultResponse<PaymentUserGetRes> getPaymentUser(@ParameterObject @ModelAttribute PaymentUserGetReq req) {
        PaymentUserGetRes res = service.getPaymentUser(req);
        return ResultResponse.<PaymentUserGetRes>builder()
                .statusCode("200")
                .resultMsg("유저 결제 내역 조회 완료")
                .resultData(res)
                .build();
    }
}
