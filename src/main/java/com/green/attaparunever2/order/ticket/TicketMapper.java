package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    int insTicket(TicketPostReq p);
}
