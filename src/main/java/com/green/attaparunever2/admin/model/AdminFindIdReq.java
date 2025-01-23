package com.green.attaparunever2.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminFindIdReq {
    @Schema(description = "관리자 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "이메일", example = "xxxx@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
