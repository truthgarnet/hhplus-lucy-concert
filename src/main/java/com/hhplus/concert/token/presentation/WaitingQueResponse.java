package com.hhplus.concert.token.presentation;

import com.hhplus.concert.token.application.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WaitingQueResponse {

    private ProgressStatus status;
    private int waiting;

}
