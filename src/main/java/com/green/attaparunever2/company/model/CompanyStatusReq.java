package com.green.attaparunever2.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyStatusReq {
    @Schema(description = "사업자등록번호", example = "3848801297", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("bNo")
    private String bNo;
}
