package com.hhplus.concert.concertReservation.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.concertReservation.infra.ConcertReservationJpaRepository;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import com.hhplus.concert.concertReservation.presentation.RegistrationResponse;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.token.infra.TokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ConcertReservationService {

    @Autowired
    private ConcertReservationJpaRepository concertReservationJpaRepository;

    public ConcertReservationEntity reservation(RegistrationRequest request) {
        String userId = request.getUserId();
        Long seatId = request.getSeatId();
        Long concertId = request.getConcertId();
        int status = 0; // 예약
        ConcertReservationEntity concertReservation = new ConcertReservationEntity(userId, concertId, seatId, status);
        ConcertReservationEntity saveReservation = concertReservationJpaRepository.save(concertReservation);

        return saveReservation;
    }

    public void checkRegistrationTime(Long concertId, String userId) {
         ConcertReservationEntity concertReservation = concertReservationJpaRepository.findByConcertIdAndUserId(concertId, userId)
                 .orElseThrow(() -> new CustomException(ErrorCode.DATABASE_ERROR));

         LocalDateTime now = LocalDateTime.now();
         if (concertReservation.getCreatedDate().plusMinutes(5).isAfter(now)) {
             throw new CustomException(ErrorCode.RESERVATION_ERROR);
         }
    }
}
