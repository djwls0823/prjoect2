package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.PaymentRestaurantDto;
import com.green.attaparunever2.order.ticket.model.PaymentRestaurantGetReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentRestaurantMapper {
    List<PaymentRestaurantDto> getPaymentRestaurant(PaymentRestaurantGetReq p);
}
