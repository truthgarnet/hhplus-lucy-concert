package com.hhplus.concert.concertReservation.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class RegistrationRequest {

    private Long userId;
    private Date date;
    private Long seatId;
    private String token;

}
