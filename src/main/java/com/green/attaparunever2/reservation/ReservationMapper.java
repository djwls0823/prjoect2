package com.green.attaparunever2.reservation;

import com.green.attaparunever2.reservation.model.ReservationPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    int postReservation(ReservationPostReq req);
}
