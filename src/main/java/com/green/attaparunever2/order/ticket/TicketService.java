package com.green.attaparunever2.order.ticket;

import com.green.attaparunever2.order.ticket.model.TicketGetRes;
import com.green.attaparunever2.order.ticket.model.TicketGetReq;
import com.green.attaparunever2.order.ticket.model.TicketPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketMapper mapper;

    public long postTicket(TicketPostReq p) {
        mapper.postTicket(p);
        return p.getTicketId();
    }

    public TicketGetRes getTicket(TicketGetReq p) {
        TicketGetRes res = mapper.getTicket(p);
        if (res == null) {
            throw new RuntimeException("해당 주문에 대한 식권이 존재하지 않습니다.");
        }
        return res;
    }

}
