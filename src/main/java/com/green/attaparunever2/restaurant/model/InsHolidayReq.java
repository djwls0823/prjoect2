package com.green.attaparunever2.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsHolidayReq {
    @Schema(title = "레스토랑 PK", example = "1")
    private long restaurantId;
    @Schema(title = "휴무일", example = "2025-03-22")
    private String closedDays;
}
