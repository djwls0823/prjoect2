package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetRes {
    private long userId;
    private long companyId;
    private String companyName;
    private String roleId;
    private String uid;
    private String name;
    private String pic;
    private String email;
    private String phone;
    private int point;
}
