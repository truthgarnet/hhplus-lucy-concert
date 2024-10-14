package com.hhplus.concert.concertReservation.presentation;

import com.hhplus.concert.concert.Concert;
import com.hhplus.concert.user.application.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponse {

    private User user;
    private Concert concert;
    private LocalDateTime paymentDate;

}
