package com.green.attaparunever2.reservation;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.reservation.model.ReservationDto;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import com.green.attaparunever2.reservation.scheduler.ReservationScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationScheduler reservationScheduler;
    private final SimpMessagingTemplate messagingTemplate;

    public long postReservation(ReservationPostReq req) {
        // 현재 예약이 있는지 검사
        ReservationDto reservationDto = reservationMapper.selActiveReservationByUserId(req.getUserId());

        if(reservationDto != null) {
            throw new CustomException(
                    reservationDto.getReservationTime() + "에 예약한 "
                    + reservationDto.getRestaurantName() + "에 대한 예약이 존재합니다. "
                    + "예약 요청에 실패했습니다."
                    , HttpStatus.BAD_REQUEST);
        }

        // 예약 등록
        int result = reservationMapper.postReservation(req);

        if (result == 0) {
            throw new CustomException("예약 요청에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        // 예약 10분 뒤 업데이트 안할 시 취소 처리할 스케줄러 실행
        reservationScheduler.scheduleCancellation(req.getReservationId());

        // 생성된 예약 정보 가져옴(소켓 통신으로 보내줌.)
        ReservationDto createdReservationDto = reservationMapper.selReservationByReservationId(req.getReservationId());

        // 사장님 구독 경로로 예약 알림 메시지 전송
        messagingTemplate.convertAndSend(
                "/queue/restaurant/" + req.getRestaurantId() + "/owner/reservation",
                req
        );

        return req.getReservationId();
    }
}
