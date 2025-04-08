package com.hhplus.concert.common.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtil {

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
