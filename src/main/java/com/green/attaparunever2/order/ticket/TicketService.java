package com.green.attaparunever2.order.ticket;

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
}
