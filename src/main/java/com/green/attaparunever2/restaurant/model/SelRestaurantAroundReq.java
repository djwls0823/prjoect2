package com.green.attaparunever2.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelRestaurantAroundReq {
    @Schema(title = "검색", example = "국밥")
    private String searchFilter;
    @Schema(title = "전체, 거리순", example = "1")
    private int orderFilter;
    @Schema(title = "유저 위도", example = "35.86")
    private double userLat;
    @Schema(title = "유저 경도", example = "128.59")
    private double userLng;
    @JsonIgnore
    private double sysMinLng;
    @JsonIgnore
    private double sysMinLat;
    @JsonIgnore
    private double sysMaxLat;
    @JsonIgnore
    private double sysMaxLng;

}
