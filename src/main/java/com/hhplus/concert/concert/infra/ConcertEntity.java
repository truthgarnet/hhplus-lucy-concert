package com.hhplus.concert.concert.infra;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "concert")
@Getter
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long concertId;

    @Column(length = 100)
    private String concertName;
    private String concertLocation;
    private int concertFee;
    private Long concertItemId;
}
