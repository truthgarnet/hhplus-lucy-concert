package com.hhplus.concert.concertReservation.presentation;

import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationResponse {

    private Long concertId;
    private Long seatId;
    private LocalDateTime registrationDate;

}
