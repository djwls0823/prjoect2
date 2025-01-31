package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.PaymentRestaurantDto;
import com.green.attaparunever2.order.ticket.model.PaymentRestaurantGetReq;
import com.green.attaparunever2.order.ticket.model.PaymentRestaurantGetRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRestaurantService {
    private final PaymentRestaurantMapper mapper;

    public PaymentRestaurantGetRes getPaymentRestaurant(PaymentRestaurantGetReq p) {
        PaymentRestaurantGetRes res = new PaymentRestaurantGetRes();

        List<PaymentRestaurantDto> paymentList = mapper.getPaymentRestaurant(p);
        res.setPaymentList(paymentList);

        return res;
    }
}
