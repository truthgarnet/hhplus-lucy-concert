package com.hhplus.concert.concertItem.infra;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "concert_item")
@Getter
public class ConcertItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long concertItemId;

    private Date reservationStart;
    private Date reservationEnd;
    private Date concertStart;
    private int seatId;
}
