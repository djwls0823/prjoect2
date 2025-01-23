package com.green.attaparunever2.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserMailVerificationDTO {
    private long userId;
    private String token;
    private String expiredDate;
    private String createdAt;
}
