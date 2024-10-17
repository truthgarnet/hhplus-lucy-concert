package com.hhplus.concert.concertItem.infra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "concert_item")
@Getter
@AllArgsConstructor
public class ConcertItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertItemId;
    private List<Long> seatIds;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private LocalDate concertStart;

}
