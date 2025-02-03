package com.green.attaparunever2.reservation;

import com.green.attaparunever2.common.model.ResultResponse;
import com.green.attaparunever2.reservation.model.ReservationPostReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "예약 요청")
    public ResultResponse<Integer> postReservation(@RequestBody ReservationPostReq req) {
        int result = reservationService.postReservation(req);
        return ResultResponse.<Integer>builder()
                             .statusCode("200")
                             .resultMsg("예약 요청 완료")
                             .resultData(result)
                             .build();
    }
}
