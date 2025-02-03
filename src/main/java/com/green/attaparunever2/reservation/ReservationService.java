package com.green.attaparunever2.reservation;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.order.OrderService;
import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.order.model.OrderDetailPostReq;
import com.green.attaparunever2.order.model.OrderPostReq;
import com.green.attaparunever2.order.ticket.TicketMapper;
import com.green.attaparunever2.order.ticket.model.TicketSelDto;
import com.green.attaparunever2.reservation.model.ReservationDto;
import com.green.attaparunever2.reservation.model.ReservationInsDto;
import com.green.attaparunever2.reservation.model.ReservationMenuDto;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import com.green.attaparunever2.reservation.scheduler.ReservationScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationScheduler reservationScheduler;
    private final SimpMessagingTemplate messagingTemplate;
    private final OrderService orderService;
    private final TicketMapper ticketMapper;

    @Transactional
    public int postReservation(ReservationPostReq req) {
        // 현재 예약이 있는지 검사
        ReservationDto reservationDto = reservationMapper.selActiveReservationByUserId(req.getUserId());

        if(reservationDto != null) {
            if(reservationDto.getReservationStatus() == 1) {
                TicketSelDto ticketSelDto = ticketMapper.selTicketByOrderId(reservationDto.getOrderId());

                // 식권이 사용되지 않았다면 아직 예약이 미완료 되었다고 판단.
                if(ticketSelDto == null || ticketSelDto.getTicketStatus() != 1) {
                    throw new CustomException(
                            reservationDto.getReservationTime() + "에 예약한 "
                                    + reservationDto.getRestaurantName() + "에 대한 예약이 존재합니다. "
                                    + "예약 요청에 실패했습니다."
                            , HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new CustomException(
                        reservationDto.getReservationTime() + "에 예약한 "
                                + reservationDto.getRestaurantName() + "에 대한 예약이 존재합니다. "
                                + "예약 요청에 실패했습니다."
                        , HttpStatus.BAD_REQUEST);
            }
        }

        // 주문정보 생성
        OrderPostReq orderData = new OrderPostReq();

        orderData.setUserId(req.getUserId());
        orderData.setRestaurantId(req.getRestaurantId());
        orderData.setReservationYn(1);
        orderData.setReservationStatus(0);

        int result = orderService.postOrder(orderData);

        if(result == 1) {
            // 주문상세정보 생성
            for(ReservationMenuDto reservationMenuDto : req.getMenuList()) {
                OrderDetailPostReq orderDetailData = new OrderDetailPostReq();

                orderDetailData.setOrderId(orderData.getOrderId());
                orderDetailData.setMenuId(reservationMenuDto.getMenuId());
                orderDetailData.setMenuCount(reservationMenuDto.getMenuCount());

                result = orderService.postOrderDetail(orderDetailData);

                if(result != 1) {
                    throw new CustomException("주문 상세 정보 생성에 실패 하였습니다.", HttpStatus.BAD_REQUEST);
                }
            }

            // 예약 정보 생성
            ReservationInsDto reservationInsDto = new ReservationInsDto();

            reservationInsDto.setOrderId(orderData.getOrderId());
            reservationInsDto.setReservationPeopleCount(req.getReservationPeopleCount());
            reservationInsDto.setReservationTime(req.getReservationTime());
            reservationInsDto.setUserPhone(req.getUserPhone());

            // 예약 등록
            result = reservationMapper.postReservation(reservationInsDto);

            if (result == 0) {
                throw new CustomException("예약 요청에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }

            // 예약 10분 뒤 업데이트 안할 시 취소 처리할 스케줄러 실행
            reservationScheduler.scheduleCancellation(reservationInsDto.getReservationId());

            // 생성된 예약 정보 가져옴(소켓 통신으로 보내줌.)
            ReservationDto createdReservationDto = reservationMapper.selReservationByReservationId(reservationInsDto.getReservationId());

            // 사장님 구독 경로로 예약 알림 메시지 전송
            messagingTemplate.convertAndSend(
                    "/queue/restaurant/" + req.getRestaurantId() + "/owner/reservation",
                    req
            );

            // 일단 무조건 승인 되었다고 처리함.
            OrderAccessPatchReq p = new OrderAccessPatchReq();
            p.setOrderId(orderData.getOrderId());
            p.setReservationStatus(1);

            result = orderService.updOrderAccess(p);
        } else {
            throw new CustomException("주문정보 생성에 실패 하였습니다.", HttpStatus.BAD_REQUEST);
        }

        return result;
    }
}
