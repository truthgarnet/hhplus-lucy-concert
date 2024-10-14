package com.hhplus.concert.concertReservation.presentation;

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
