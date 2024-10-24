package com.hhplus.concert.concertItem.infra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "concert_item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConcertItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertItemId;
    private Long seatId;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private LocalDate concertStart;
    private Long concertId;

}
