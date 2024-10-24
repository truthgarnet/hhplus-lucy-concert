package com.hhplus.concert.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ERROR("API오류", "에러가 발생했습니다.", 500),
    DATABASE_ERROR("API오류",  "데이터베이스 오류", 500),
    TOKEN_ERROR("토큰오류", "토큰 오류가 발생했습니다.", 500),
    SEAT_ERROR("좌석오류", "좌석이 충분하지 않습니다.", 500),
    MONEY_ERROR("잔액오류", "잔액이 부족합니다.", 500),
    CHARGE_ERROR("충전오류", "충전할 금액이 0이하 입니다.", 500),
    RESERVATION_ERROR("예약오류", "예약시간이 초과되었습니다.", 500);


    private final String error;
    private final String msg;
    private final int code;
}
