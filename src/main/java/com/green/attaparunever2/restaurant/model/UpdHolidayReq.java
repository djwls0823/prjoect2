package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "휴무일 변경")
public class UpdHolidayReq {
    @Schema(title = "식당 PK")
    private long restaurantId;
    @Schema(title = "휴일 ID")
    private long holidayId;
    @Schema(title = "휴무일", example = "2025-01-29")
    private String closedDays;
}
