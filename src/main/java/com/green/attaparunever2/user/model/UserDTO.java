package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
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

    @JsonIgnore // swagger 표시 안되지만, 응답 때 빼는 역할도 한다.
    private String upw;
}
