package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminMailVerificationDTO {
    private long adminId;
    private String token;
    private String expiredDate;
    private String createdAt;
}
