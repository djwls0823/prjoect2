package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "관리자 로그인")
public class AdminSignUpReq {
    @Schema(title = "관리자 권한", example = "ROLE_RESTAURANT", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleId;
    @Schema(title = "관리자 아이디", example = "asd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String aid;
    @Schema(title = "관리자 비밀번호", example = "asd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apw;
    @Schema(title = "관리자 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title = "관리자 이메일", example = "asd@naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$", message = "유효하지 않은 형식의 이메일입니다.")
    private String email;
    @Schema(title = "관리자 폰 전화", example = "01012345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @JsonIgnore
    private long adminId;
}
