package com.hhplus.concert.concertReservation.presentation;

import com.hhplus.concert.concert.infra.ConcertEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponse {

    private String userId;
    private ConcertEntity concert;
    private LocalDateTime paymentDate;

}
