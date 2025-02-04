package com.green.attaparunever2.user.user_payment_member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPaymentUserIdAndPoint {
    private long orderId;
    private long userId;
    private int point;

    public PostPaymentUserIdAndPoint(long orderId, long userId, int point) {
        this.orderId = orderId;
        this.userId = userId;
        this.point = point;
    }
}
