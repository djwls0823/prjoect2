package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
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

}
