package com.hhplus.concert.concert.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concert.infra.ConcertJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

    @Autowired
    private ConcertJpaRepository concertJpaRepository;

    public ConcertEntity getConcert(Long concertId) {
        return concertJpaRepository.findById(concertId).orElseThrow(() -> new CustomException(ErrorCode.DATABASE_ERROR));
    }
}
