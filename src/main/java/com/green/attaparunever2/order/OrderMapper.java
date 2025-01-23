package com.green.attaparunever2.order;

import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.order.model.OrderDetailPostReq;
import com.green.attaparunever2.order.model.OrderPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int postOrder(OrderPostReq p);
    int postOrderDetail(OrderDetailPostReq p);
    int updOrderAccess(OrderAccessPatchReq p);
}
