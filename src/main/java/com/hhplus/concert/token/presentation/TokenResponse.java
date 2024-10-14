package com.hhplus.concert.token.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {

    private String token;
    private LocalDateTime expires;

}
