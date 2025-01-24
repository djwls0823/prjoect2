package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.user.user_payment_member.model.UserGetPaymentInfoReq;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPaymentInfoRes;
import com.green.attaparunever2.user.user_payment_member.model.UserGetPointRes;
import com.green.attaparunever2.user.user_payment_member.model.UserPostPaymentReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPaymentMemberMapper {
    UserGetPointRes getPoint(long userId);
    int getOrderPrice(long orderId);
    int insertPaymentMember(UserPostPaymentReq p);
    int getPaymentMember(long orderId);
    UserGetPaymentInfoRes getPaymentInfo(UserGetPaymentInfoReq p);
}