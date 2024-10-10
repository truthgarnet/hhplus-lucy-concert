package com.hhplus.concert.dto;

import com.hhplus.concert.domain.Concert;
import com.hhplus.concert.domain.User;
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
