package com.green.attaparunever2.order.ticket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRestaurantDto {
    private int reservationYn;
    private long menuId;
    private String menuName;
    private int totalCount;
    private int totalSales;
    private String orderDate;
}
