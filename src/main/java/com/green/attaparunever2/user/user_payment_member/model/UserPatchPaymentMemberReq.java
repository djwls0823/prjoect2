package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "나에게 온 결제 요청 정보 수정")
public class UserPatchPaymentMemberReq {
    @Schema(title = "주문 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long orderId;
    @Schema(title = "사용자 PK", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private long userId;
    @Schema(title = "승인 여부", example = "1", description = "0: 미승인, 1: 승인, 2: 거부")
    private int approvalStatus;
}
