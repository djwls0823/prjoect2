package com.green.attaparunever2.user.user_payment_member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserGetPaymentInfoRes {
    @Schema(title = "결제금액")
    private int price;
    @Schema(title = "식당명")
    private String restaurantName;
    @Schema(title = "요청자")
    private String name;
}
