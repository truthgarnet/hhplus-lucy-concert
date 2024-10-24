package com.hhplus.concert.concert.infra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "concert")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertId;

    @Column(length = 100)
    private String concertName;
    private String concertLocation;
    private int concertFee;
    private Long concertItemId;
}
