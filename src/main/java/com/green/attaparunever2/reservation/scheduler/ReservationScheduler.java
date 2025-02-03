package com.green.attaparunever2.reservation.scheduler;

import com.green.attaparunever2.order.OrderMapper;
import com.green.attaparunever2.order.model.OrderAccessPatchReq;
import com.green.attaparunever2.reservation.ReservationMapper;
import com.green.attaparunever2.reservation.model.ReservationDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationScheduler {
    private final DelayQueue<ReservationTask> queue = new DelayQueue<>();
    private final ReservationMapper reservationMapper;
    private final OrderMapper orderMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public ReservationScheduler(ReservationMapper reservationMapper
            , OrderMapper orderMapper
            , SimpMessagingTemplate messagingTemplate) {
        this.reservationMapper = reservationMapper;
        this.orderMapper = orderMapper;
        this.messagingTemplate = messagingTemplate;
        startProcessing(); // 큐를 처리하는 쓰레드 실행
    }

    // 예약 등록 후 10분 뒤 자동 취소 작업 추가
    public void scheduleCancellation(Long reservationId) {
        queue.add(new ReservationTask(reservationId, TimeUnit.MINUTES.toMillis(10)));
    }

    // 큐에서 작업 꺼내서 실행하는 쓰레드
    private void startProcessing() {
        new Thread(() -> {
            while (true) {
                try {
                    ReservationTask task = queue.take(); // 10분 후 실행됨
                    cancelIfNotUpdated(task.getReservationId());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // 10분 후에도 상태가 "예약됨"이면 "취소됨"으로 변경
    private void cancelIfNotUpdated(Long reservationId) {
        ReservationDto reservationDto = reservationMapper.selReservationByReservationId(reservationId);

        if (reservationDto != null) {
            if (reservationDto.getReservationStatus() == 0) {
                OrderAccessPatchReq p = new OrderAccessPatchReq();
                p.setOrderId(reservationDto.getOrderId());
                p.setReservationStatus(3);

                int result = orderMapper.updOrderAccess(p);

                if (result == 1) {
                    reservationDto.setReservationStatus(p.getReservationStatus());

                    // 사용자에게 예약 데이터 전송
                    messagingTemplate.convertAndSend(
                            "/queue/reservation/" + reservationDto.getOrderId() + "/user/reservation",
                            reservationDto
                    );
                }
            }
        }
    }
}


