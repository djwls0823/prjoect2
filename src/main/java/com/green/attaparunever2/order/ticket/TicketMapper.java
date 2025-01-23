package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.TicketDto;
import com.green.attaparunever2.order.ticket.model.TicketGetReq;
import com.green.attaparunever2.order.ticket.model.TicketGetRes;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    int postTicket(TicketPostReq p);
    TicketDto getTicket(TicketGetReq p);
}
