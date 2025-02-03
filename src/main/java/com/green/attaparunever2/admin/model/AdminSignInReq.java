package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "회원 로그인 정보")
public class AdminSignInReq {
    @Schema(description = "아이디", example = "10000001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;
    @Schema(description = "비밀번호", example = "qwer12#$", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pw;
}
