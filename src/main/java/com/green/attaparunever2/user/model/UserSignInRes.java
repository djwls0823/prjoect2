package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "로그인 응답")
public class UserSignInRes {
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
