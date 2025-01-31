package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(title = "식당 정보 수정")
public class UpdRestaurantReq {
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "식당 이름")
    private String restaurantName;
    @Schema(title = "식당 주소")
    private String restaurantAddress;
    @Schema(title = "식당 전화번호")
    private String restaurantNumber;
    @Schema(title = "식당 운영 시간")
    private String operatingHours;
    @Schema(title = "식당 설명")
    private String restaurantDescription;
    @Schema(title = "식당 상태")
    private int status;
    @Schema(title = "최대 수용 인원수")
    private int maxCapacity;
    @Schema(title = "위도")
    private double lat;
    @Schema(title = "경도")
    private double lng;
}