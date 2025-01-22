package com.green.attaparunever2.restaurant.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelHolidayRes {
    @Schema(title = "휴무일")
    private String closedDays;
}
