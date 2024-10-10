package com.hhplus.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentRequest {

    private Long userId;
    private int price;
    private String token;

}
