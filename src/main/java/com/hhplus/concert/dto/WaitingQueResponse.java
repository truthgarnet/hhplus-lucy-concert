package com.hhplus.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WaitingQueResponse {

    private Long userId;
    private int waitingOrder;

}
