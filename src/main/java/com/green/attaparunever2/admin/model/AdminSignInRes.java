package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "로그인 응답")
public class AdminSignInRes {
    private long adminId;
    private String roleId;
    private String aid;
    private String name;
    private String email;
    private String phone;

    @JsonIgnore // swagger 표시 안되지만, 응답 때 빼는 역할도 한다.
    private String apw;
}
