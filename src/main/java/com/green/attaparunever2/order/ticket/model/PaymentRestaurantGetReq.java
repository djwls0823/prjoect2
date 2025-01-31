package com.green.attaparunever2.order.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@Schema(title = "매장 매출 내역 조회 요청")
@ToString
public class PaymentRestaurantGetReq {
    private long restaurantId;
    private String startDate;
    private String endDate;

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

