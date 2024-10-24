package com.hhplus.concert.seat.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.seat.infra.SeatJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatJpaRepository seatJpaRepository;

    public SeatEntity getSeat(Long seatId) {
        return seatJpaRepository.findBySeatId(seatId).orElseThrow(() -> new CustomException(ErrorCode.ERROR));
    }
}
