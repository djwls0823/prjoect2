package com.green.attaparunever2.order;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.model.OrderPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Tag(name = "주문", description = "주문 관리")
public class OrderController {
    private final OrderService service;

    @PostMapping
    @Operation(summary = "주문 등록")
    public ResultResponse<Long> postOrder(@Valid @RequestBody OrderPostReq p
                                        , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("주문 등록 실패")
                    .resultData(0L)
                    .build();
        }

        service.postOrder(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 등록 완료")
                .resultData(1L)
                .build();
    }

}
