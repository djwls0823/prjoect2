package com.green.attaparunever2.order;

import com.green.attaparunever2.order.model.OrderDetailPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    int postOrderDetail(OrderDetailPostReq p);
}
