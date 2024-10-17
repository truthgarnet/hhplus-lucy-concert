package com.hhplus.concert.concertReservation.infra;

import com.hhplus.concert.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "concert_reservation")
@Getter
public class ConcertReservationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long concertReservationId;

    private String userId;
    private Long concertId;
    private Long seatId;
    private int status; // 0: reservation, 1: cancel, 2: charge

    public ConcertReservationEntity(String userId, Long concertId, Long seatId, int status) {
        this.userId = userId;
        this.concertId = concertId;
        this.seatId = seatId;
        this.status = status;
    }
}
