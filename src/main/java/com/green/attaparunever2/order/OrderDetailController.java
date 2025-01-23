package com.green.attaparunever2.order;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.model.OrderDetailPostReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order/detail")
@Tag(name = "주문 상세 정보", description = "주문 상세 정보 관리")
public class OrderDetailController {
    private final OrderDetailService service;

    public ResultResponse<Long> postOrderDetail(@RequestBody OrderDetailPostReq p) {
        service.postOrderDetail(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("주문 상세 정보 등록 완료")
                .resultData(1L)
                .build();
    }
}
