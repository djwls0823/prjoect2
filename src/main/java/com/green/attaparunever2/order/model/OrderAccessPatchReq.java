package com.green.attaparunever2.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "주문 상태 변경")
public class OrderAccessPatchReq {
    @Schema(title = "주문 PK")
    private long orderId;

    @NotNull
    @Positive
    @Schema(title = "예약 상태", requiredMode = Schema.RequiredMode.REQUIRED)
    private int reservationStatus;
}
