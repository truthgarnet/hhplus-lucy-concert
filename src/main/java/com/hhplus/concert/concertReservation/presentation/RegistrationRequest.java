package com.hhplus.concert.concertReservation.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationRequest {

    private String userId;
    private Long concertId;
    private Long seatId;

}
