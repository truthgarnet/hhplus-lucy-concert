package com.hhplus.concert.common.exception;

public record ErrorResponse(
        int code,
        String message
) {
}
