package com.hhplus.concert.userCharge.presentation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserChargeResponse {

    private String userId;
    private int balance;

}
