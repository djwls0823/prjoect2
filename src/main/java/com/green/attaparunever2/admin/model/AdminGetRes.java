package com.green.attaparunever2.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminGetRes {
    private long adminId;
    private String roleId;
    private String aid;
    private String name;
    private String email;
    private String phone;
}
