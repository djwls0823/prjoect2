package com.green.attaparunever2.order;

import com.green.attaparunever2.order.model.OrderDetailPostReq;
import com.green.attaparunever2.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper mapper;

    public long postOrder(OrderPostReq p) {
        mapper.postOrder(p);
        return p.getOrderId();
    }

    public long postOrderDetail(OrderDetailPostReq p) {
        mapper.postOrderDetail(p);
        return p.getOrderDetailId();
    }
}
