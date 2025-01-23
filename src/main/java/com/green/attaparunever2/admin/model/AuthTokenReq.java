package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthTokenReq {
    private long adminId;
    private String token;
}
