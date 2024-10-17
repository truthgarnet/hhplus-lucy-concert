package com.hhplus.concert.concertReservation.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentRequest {

    private String userId;
    private Long seatId;
    private int price;
    private String token;

}
