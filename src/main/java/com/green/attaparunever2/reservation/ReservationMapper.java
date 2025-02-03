package com.green.attaparunever2.reservation;

import com.green.attaparunever2.reservation.model.ReservationDto;
import com.green.attaparunever2.reservation.model.ReservationInsDto;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    int postReservation(ReservationInsDto req);
    ReservationDto selActiveReservationByUserId(long userId);
    ReservationDto selReservationByReservationId(long reservationId);
    ReservationDto selReservationByOrderId(long userId);
}
