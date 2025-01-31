package com.green.attaparunever2.reservation;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public int postReservation(ReservationPostReq req) {
        int result = reservationMapper.postReservation(req);
        if (result == 0) {
            throw new CustomException("예약 요청에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

        // 사장님 구독 경로로 예약 알림 메시지 전송
        messagingTemplate.convertAndSend(
                "/queue/restaurant/" + req.getRestaurantId() + "/owner/reservation",
                req
        );

        return result;
    }
}
