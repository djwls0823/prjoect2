package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.model.OrderDto;
import com.green.attaparunever2.order.model.OrderGetReq;
import com.green.attaparunever2.order.ticket.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketMapper mapper;
    private final OrderMapper orderMapper;
    private final PaymentUserMapper paymentUserMapper;

    public long postTicket(TicketPostReq p) {

        OrderDto order = orderMapper.getOrder(new OrderGetReq(p.getOrderId()));
        if (order == null) {
            throw new CustomException("해당 주문이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        long orderId = order.getOrderId();
        int totalPrice = order.getTotalPrice();

        // 사용자의 포인트 조회
        PaymentUserDto userPoint = paymentUserMapper.getPoint(orderId);

        if (userPoint == null) {
            throw new CustomException("등록된 포인트가 없습니다.", HttpStatus.NOT_FOUND);
        }

        int point = userPoint.getPoint();

        if (point < totalPrice) {
            throw new RuntimeException("사용자의 포인트가 부족합니다.");
        }

        if (point != totalPrice) {
            throw new RuntimeException("포인트와 주문 금액이 일치하지 않습니다.");
        }

        return mapper.postTicket(p);
    }

    public TicketGetRes getTicket(TicketGetReq p) {
        TicketGetRes res = new TicketGetRes();

        TicketDto ticket = mapper.getTicket(p.getOrderId());
        res.setTicket(ticket);

        if (ticket == null) {
            throw new RuntimeException("해당 주문에 대한 식권이 존재하지 않습니다.");
        }

        return res;
    }

}
