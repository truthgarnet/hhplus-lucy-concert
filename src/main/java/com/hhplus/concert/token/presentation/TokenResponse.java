package com.hhplus.concert.token.presentation;

import com.hhplus.concert.token.application.ProgressStatus;
import com.hhplus.concert.token.infra.TokenEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {

    private String token;
    private ProgressStatus status;
    private LocalDateTime expires;

    public static TokenResponse from(TokenEntity tokenEntity) {
        TokenResponse response = new TokenResponse();
        response.token = tokenEntity.getToken();
        response.status = tokenEntity.getStatus();
        response.expires = tokenEntity.getExpiredDate();

        return response;
    }

}
