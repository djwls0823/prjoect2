package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPostPaymentMemberReq {
    @Schema(title = "주문 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;
    @Schema(title = "사용자 PK", example = "[1, 2, 3]", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> userId;
    @Schema(title = "승인 포인트", example = "[10000, 20000, 30000]")
    private List<Integer> point;
}
