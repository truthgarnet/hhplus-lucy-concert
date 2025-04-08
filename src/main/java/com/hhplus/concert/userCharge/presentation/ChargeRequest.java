package com.hhplus.concert.userCharge.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChargeRequest {

    private String userId;
    private int amount;

}
