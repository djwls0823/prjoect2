package com.green.attaparunever2.order;

import com.green.attaparunever2.order.model.OrderDetailPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailMapper mapper;

    public long postOrderDetail(OrderDetailPostReq p) {
        mapper.postOrderDetail(p);
        return p.getOrderDetailId();
    }
}
