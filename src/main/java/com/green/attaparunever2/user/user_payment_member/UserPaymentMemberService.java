package com.green.attaparunever2.user.user_payment_member;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.user.model.UserSignInRes;
import com.green.attaparunever2.user.user_payment_member.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPaymentMemberService {
    private final UserPaymentMemberMapper userPaymentMemberMapper;

    //사용자 포인트 조회
    public UserGetPointRes getPoint(long userId) {
        UserGetPointRes point = null;

        try {
            point = userPaymentMemberMapper.getPoint(userId);
        }catch (Exception e) {
            throw new CustomException("포인트 조회시 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }

        return point;
    }

    //결제 등록
    @Transactional
    public int postPayment(UserPostPaymentReq p) {
        int result = 0;
        try {
            log.debug("orderId::" + p.getOrderId());
            /*
            함께 결제가 아니라 1명이 계산해야하는 경우
                1.결제해야하는 사람이 가지고있는 포인트를 확인
                2.주문금액보다 포인트가 많은지 확인
                    결제금액은 여러명일시 총 금액/인원수로 한 사람이 결제해야하는 금액을 가져온다

            직관적으로 개발하는편이 우선은 좋을 것 같음
            */

//          1.결제해야하는 사람이 가지고있는 포인트를 확인
            UserGetPointRes userPointInfo = userPaymentMemberMapper.getPoint(p.getUserId());

//          2.주문금액보다 포인트가 많은지 확인
            int price = userPaymentMemberMapper.getOrderPrice(p.getOrderId());

//          1번과 2번을 비교하여 결제할 수 있는지 체크
            if(userPointInfo.getPoint() >= price)
            {
                p.setPoint(price);
                result = userPaymentMemberMapper.insertPaymentMember(p);
            } else {
                int amount = price - userPointInfo.getPoint();
                String msg = "결제금액이("+amount+"원) 부족합니다.";
                throw new CustomException(msg, HttpStatus.OK);
            }

        } catch (Exception e) {
            String msg = "결제정보 등록시 에러가 발생하였습니다.";
            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    //결제 인원 조회
    public int getPaymentMember(long orderId) {
        int targetCnt = 0;
        try {
            targetCnt = userPaymentMemberMapper.getPaymentMember(orderId);

        } catch (Exception e) {
            String msg = "결제 인원 조회시 에러가 발생하였습니다.";
            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
        }

        return targetCnt;
    }

    //내게 온 결제 승인요청 정보조회
    public UserGetPaymentInfoRes getPaymentInfo(UserGetPaymentInfoReq p) {
        UserGetPaymentInfoRes result = null;
        try {
            /*
             내게 온 결제 정보는 user_payment_member 테이블에 order_id, user_id로 조회 할 수 있지만,
             사용자 화면에 승인요청 정보를 표출시 메뉴명을 보여주고싶다면
              + 식당명, 요청자, 결제id (PK)
             */
            result = userPaymentMemberMapper.getPaymentInfo(p);
        } catch (Exception e) {
            String msg = "내게 온 결제 승인요청 정보조회시 에러가 발생하였습니다.";
            throw new CustomException(msg, HttpStatus.BAD_REQUEST);
        }

        return result;
    }

    //나에게 온 결제 요청 정보 승인 및 거부
    public int patchPaymentMember(UserPatchPaymentMemberReq p) {
        int result = userPaymentMemberMapper.patchPaymentMember(p);

        return result;
    }

    public int postPaymentMember(UserPostPaymentMemberReq p) {
        // userId와 point 리스트의 길이가 일치하지 않으면 예외 처리
        if (p.getUserId().size() != p.getPoint().size()) {
            throw new IllegalArgumentException("userId와 point 리스트의 크기가 일치하지 않습니다.");
        }

        // userId와 point를 결합하여 새로운 리스트 생성
        List<PostPaymentUserIdAndPoint> paymentMembers = new ArrayList<>();
        for (int i = 0; i < p.getUserId().size(); i++) {
            // Long -> long, Integer -> int로 변환하여 PostPaymentUserIdAndPoint 객체 생성
            paymentMembers.add(new PostPaymentUserIdAndPoint(
                    p.getOrderId(),
                    p.getUserId().get(i).longValue(),  // Long -> long
                    p.getPoint().get(i).intValue()     // Integer -> int
            ));
        }

        // 변환된 리스트를 매퍼로 전달
        return userPaymentMemberMapper.postPaymentMember(paymentMembers);
    }
}
