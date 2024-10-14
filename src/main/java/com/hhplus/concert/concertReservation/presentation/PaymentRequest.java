package com.hhplus.concert.concertReservation.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentRequest {

    private Long userId;
    private int price;
    private String token;

}
