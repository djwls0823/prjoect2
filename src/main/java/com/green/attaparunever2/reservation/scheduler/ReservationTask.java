package com.green.attaparunever2.reservation.scheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ReservationTask implements Delayed {
    private final long executeTime; // 실행 시간
    private final Long reservationId;

    public ReservationTask(Long reservationId, long delay) {
        this.reservationId = reservationId;
        this.executeTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeTime, ((ReservationTask) o).executeTime);
    }

    public Long getReservationId() {
        return reservationId;
    }
}
