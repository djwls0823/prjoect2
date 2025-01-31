package com.green.attaparunever2.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyStatusRes {
    @Schema(description = "API 조회 상태코드", example = "200", requiredMode = Schema.RequiredMode.REQUIRED)
    private String statusCode;
    @Schema(description = "API 조회 상태코드명", example = "OK", requiredMode = Schema.RequiredMode.REQUIRED)
    private String statusCodeNm;
    @Schema(description = "사업자등록번호", example = "0000000000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bNo;
    @Schema(description = "납세자 상태(명칭). 01:계속사업자, 02:휴업자, 03:폐업자", example = "01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bStt;
    @Schema(description = "납세자 상태(코드). 01:계속사업자, 02:휴업자, 03:폐업자", example = "01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bSttCd;
    @Schema(description = "폐업일", example = "YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endDt;
}
