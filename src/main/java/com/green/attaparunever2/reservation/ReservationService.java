package com.green.attaparunever2.reservation;

import com.green.attaparunever2.common.excprion.CustomException;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public int postReservation(ReservationPostReq req) {
        int result = reservationMapper.postReservation(req);
        if (result == 0) {
            throw new CustomException("예약 요청에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}
