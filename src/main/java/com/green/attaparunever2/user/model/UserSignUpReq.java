package com.green.attaparunever2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "유저 회원가입")
public class UserSignUpReq {
    @JsonIgnore
    private long userId;

    @JsonIgnore
    private String pic;

    @NotEmpty(message = "회사 PK 입력해주세요.")
    @Schema(title = "회사 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long companyId;

    @NotEmpty
    @Schema(title = "유저 아이디(회사번호 + 사원 번호)", example = "10000001")
    private String uid;

    @NotEmpty(message = "사용자 이름을 입력해주세요.")
    @Schema(title = "사용자 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Schema(title = "사용자 이메일", example = "test@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Schema(title = "사용자 비밀번호", example = "qwer12#$", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;

    @NotEmpty(message = "연락처를 입력해주세요.")
    @Schema(title = "사용자 연락처", example = "01012345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
}
