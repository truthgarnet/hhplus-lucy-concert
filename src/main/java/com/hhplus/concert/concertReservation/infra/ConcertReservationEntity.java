package com.hhplus.concert.concertReservation.infra;

import com.hhplus.concert.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "concert_reservation")
@Getter
public class ConcertReservationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long concertReservationId;

    private Long userId;
    private Long seatId;
    private int status; // 0: reservation, 1: cancel, 2: charge
}
