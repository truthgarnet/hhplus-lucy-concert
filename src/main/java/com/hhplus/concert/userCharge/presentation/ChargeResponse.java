package com.hhplus.concert.userCharge.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChargeResponse {

    private int chargeAmount;
    private int userAmount;
    private LocalDateTime chargeDate;

}
