package com.hhplus.concert.seat.infra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private int allSeat;
    private int remainderSeat;
    private Long concertItemId;

}
