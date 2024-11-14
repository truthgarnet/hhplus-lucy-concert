package com.hhplus.concert.concert.application;

import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concertReservation.application.ConcertReservationService;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.concertReservation.presentation.PaymentRequest;
import com.hhplus.concert.concertReservation.presentation.PaymentResponse;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import com.hhplus.concert.concertReservation.presentation.RegistrationResponse;
import com.hhplus.concert.seat.application.SeatService;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.sms.application.SmsService;
import com.hhplus.concert.user.application.UserService;
import com.hhplus.concert.userCharge.application.UserChargeService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ConcertFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserChargeService userChargeService;

    @Autowired
    private ConcertReservationService concertReservationService;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private SmsService smsService;

    @Transactional
    public RegistrationResponse reservation(RegistrationRequest request) {
        ConcertReservationEntity reservation = concertReservationService.reservation(request);
        SeatEntity seat = seatService.getSeat(reservation.getSeatId());
        ConcertEntity concert = concertService.getConcert(request.getConcertId());

        return new RegistrationResponse(concert.getConcertId(), seat.getSeatId(), LocalDateTime.now());
    }

    @Transactional
    public PaymentResponse pay(Long concertId, PaymentRequest request) {
        concertReservationService.checkRegistrationTime(concertId, request.getUserId());
        userChargeService.checkValidMoney(request.getUserId(), request.getPrice());
        userChargeService.pay(concertId, request);
        userService.getUser(request.getUserId());

        smsService.sendRegistration(request.getUserId());
        ConcertEntity concert = concertService.getConcert(concertId);

        LocalDateTime now = LocalDateTime.now();
        return new PaymentResponse(request.getUserId(), concert, now);
    }
}
