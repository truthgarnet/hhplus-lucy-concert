package com.hhplus.concert.seat.infra;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "seat")
@Getter
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;
    private Long concertItemId;
    private int allSeat;
    private int remainderSeat;

}
