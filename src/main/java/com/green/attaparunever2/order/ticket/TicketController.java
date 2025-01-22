package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.ticket.model.TicketGetReq;
import com.green.attaparunever2.order.ticket.model.TicketGetRes;
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
@RequestMapping("order/ticket")
@Tag(name = "식권", description = "식권 관리")
public class TicketController {
    private final TicketService service;

    @PostMapping
    @Operation(summary = "식권 생성")
    public ResultResponse<Long> postTicket(@Valid @RequestBody TicketPostReq p
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultResponse.<Long>builder()
                    .statusCode("400")
                    .resultMsg("식권 생성 실패")
                    .resultData(0L)
                    .build();
        }

        service.postTicket(p);
        return ResultResponse.<Long>builder()
                .statusCode("200")
                .resultMsg("식권 생성 완료")
                .resultData(1L)
                .build();

    }

    @GetMapping
    @Operation(summary = "식권 조회")
    public ResultResponse<TicketGetRes> getTicket(@Valid @ModelAttribute long orderId) {
        TicketGetReq req = new TicketGetReq(orderId);
        TicketGetRes res = service.getTicket(req);
        return ResultResponse.<TicketGetRes>builder()
                .statusCode("200")
                .resultMsg("식권 조회 완료")
                .resultData(res)
                .build();
    }

}
