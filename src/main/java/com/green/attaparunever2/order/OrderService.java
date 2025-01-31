package com.green.attaparunever2.order;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.order.model.*;
import com.green.attaparunever2.order.ticket.PaymentUserMapper;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.TicketService;
import com.green.attaparunever2.order.ticket.model.*;
import com.green.attaparunever2.user.user_payment_member.UserPaymentMemberMapper;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPointRes;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceUnwrapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper mapper;
    private final TicketMapper ticketMapper;

    public long postOrder(OrderPostReq p) {
        mapper.postOrder(p);
        return p.getOrderId();
    }


    public long postOrderDetail(OrderDetailPostReq p) {
        mapper.postOrderDetail(p);
        return p.getOrderDetailId();
    }


    @Transactional
    public long updOrderAccess(OrderAccessPatchReq p) {
        TicketDelReq req = new TicketDelReq();
        req.setOrderId(p.getOrderId());

        int deleteResult = 0;

        try {
            if (p.getReservationStatus() == 3) {
                deleteResult = ticketMapper.delTicket(req);
            }
        } catch (Exception e) {
            throw new CustomException("식권 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        return mapper.updOrderAccess(p);
    }


    public OrderGetRes getOrder(OrderGetReq p) {
        OrderGetRes res = new OrderGetRes();
        OrderDto orderList = mapper.getOrder(p);
        res.setOrderList(orderList);

        if (orderList == null) {
            throw new RuntimeException("해당 주문에 대한 정보가 존재하지 않습니다.");
        }

        return res;
    }
}
