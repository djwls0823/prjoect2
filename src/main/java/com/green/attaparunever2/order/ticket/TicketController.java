package com.green.attaparunever2.order.ticket;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order/ticket")
@Tag(name = "식권", description = "식권 관리")
public class TicketController {
}
