package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    int postTicket(TicketPostReq p);
    TicketDto getTicket(long orderId);
    int delTicket(TicketDelReq req);
    TicketSelDto selTicketByOrderId(long orderId);
}
