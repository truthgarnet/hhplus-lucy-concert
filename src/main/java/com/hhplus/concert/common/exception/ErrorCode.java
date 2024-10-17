package com.hhplus.concert.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ERROR("API오류", "에러가 발생했습니다.", 500),
    DATABASE_ERROR("API오류",  "데이터베이스 오류", 500),
    TOKEN_ERROR("토큰오류", "토큰 오류가 발생했습니다.", 500);

    private final String error;
    private final String msg;
    private final int code;
}
