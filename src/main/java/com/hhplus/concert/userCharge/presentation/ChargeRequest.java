package com.hhplus.concert.userCharge.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChargeRequest {

    private Long userId;
    private String amount;
    private String token;

}
