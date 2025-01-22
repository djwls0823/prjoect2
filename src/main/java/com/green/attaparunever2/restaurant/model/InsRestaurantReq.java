package com.green.attaparunever2.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsRestaurantReq {
    @Schema(title = "관리자 PK", example = "3")
    private long adminId;
    @Schema(title = "카테고리 PK", example = "1")
    private long categoryId;
    @Schema(title = "식당 이름", example = "국밥")
    private String restaurantName;
    @Schema(title = "식당 주소", example = "대구광역시 달서구 신당로")
    private String restaurantAddress;
    @Schema(title = "식당 전화번호", example = "05312341234")
    private String restaurantNumber;
    @Schema(title = "식당 사업자번호", example = "1234567890")
    private String businessNumber;
    @Schema(title = "영업시간", example = "12시 ~ 22시")
    private String operatingHours;
    @Schema(title = "식당 설명", example = "돼지국밥 전문점입니다.")
    private String restaurantDescription;
    @Schema(title = "식당 좌석 수", example = "8")
    private int maxCapacity;
    @Schema(title = "위도", example = "36.4")
    private double lat;
    @Schema(title = "경도", example = "22.5")
    private double lng;
    @JsonIgnore
    private String createdAt;
}
