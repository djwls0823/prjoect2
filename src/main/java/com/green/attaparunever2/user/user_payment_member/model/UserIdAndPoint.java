package com.green.attaparunever2.user.user_payment_member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdAndPoint {
    private long userId;
    private int point;

    public UserIdAndPoint(long userId, int point) {
        this.userId = userId;
        this.point = point;
    }
}
