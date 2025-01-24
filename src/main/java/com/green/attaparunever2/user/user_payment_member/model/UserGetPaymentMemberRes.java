package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserGetPaymentMemberRes {
    @Schema(title = "결제에 해당하는 인원 수")
    private int targetCnt;
}
