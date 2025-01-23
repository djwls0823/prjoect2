package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.PaymentUserGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentUserMapper {
    List<PaymentUserGetRes> getPaymentUser(long userId);
}
