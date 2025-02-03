package com.green.attaparunever2.order;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.model.*;
import com.green.attaparunever2.order.ticket.TicketService;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Tag(name = "주문", description = "주문 관리")
public class OrderController {
    private final OrderService service;
    private final TicketService ticketService;

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

        long result = service.postOrder(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 등록 완료")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "주문 조회")
    public ResultResponse<OrderGetRes> getOrder(@ParameterObject @ModelAttribute OrderGetReq p) {
        OrderGetRes res = service.getOrder(p);
        return ResultResponse.<OrderGetRes>builder()
                .statusCode("200")
                .resultMsg("주문 조회 완료")
                .resultData(res)
                .build();
    }

    @PostMapping("/detail")
    @Operation(summary = "주문 상세 정보 등록")
    public ResultResponse<Long> postOrderDetail(@Valid @RequestBody OrderDetailPostReq p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("주문 상세 정보 등록 실패")
                    .resultData(0L)
                    .build();
        }

        long orderDetailId = service.postOrderDetail(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 상세 정보 등록 완료")
                .resultData(orderDetailId)
                .build();
    }

    @PutMapping("/access")
    @Operation(summary = "주문 상태 변경", description = "0:미승인, 1:승인, 2:거부, 3:취소")
    public ResultResponse<Long> updOrderAccess(@Valid @RequestBody OrderAccessPatchReq p
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("주문 상태 변경 실패")
                    .resultData(0L)
                    .build();
        }

        service.updOrderAccess(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 상태 변경 완료")
                .resultData(1L)
                .build();
    }

}
