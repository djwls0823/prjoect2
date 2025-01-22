package com.green.attaparunever2.order.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(title = "식권 생성 요청")
@ToString
public class TicketPostReq {
    @JsonIgnore
    private long ticketId;

    @Schema(title = "주문 PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;

    @NotNull
    @Schema(title = "만료 시간", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime expiredDate;
}
