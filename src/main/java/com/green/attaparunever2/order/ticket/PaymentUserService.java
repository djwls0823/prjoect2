package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.PaymentUserDto;
import com.green.attaparunever2.order.ticket.model.PaymentUserGetReq;
import com.green.attaparunever2.order.ticket.model.PaymentUserGetRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentUserService {
    private final PaymentUserMapper mapper;

    public PaymentUserGetRes getPaymentUser(PaymentUserGetReq p) {
        PaymentUserGetRes res = new PaymentUserGetRes();

        List<PaymentUserDto> paymentList = mapper.getPaymentUser(p);
        res.setPaymentList(paymentList);

        return res;
    }
}
