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
    @Schema(title = "거리순, 빠른 식사순", example = "36.1")
    private int orderFilter;
    @Schema(title = "유저 위도", example = "33")
    private double userLat;
    @Schema(title = "유저 경도", example = "33")
    private double userLng;
    @JsonIgnore
    private double sysLat;
    @JsonIgnore
    private double sysLng;

}
