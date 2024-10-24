package com.hhplus.concert.concert.application;

import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concertItem.application.ConcertItemService;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertReservation.application.ConcertReservationService;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.concertReservation.presentation.PaymentRequest;
import com.hhplus.concert.concertReservation.presentation.PaymentResponse;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import com.hhplus.concert.concertReservation.presentation.RegistrationResponse;
import com.hhplus.concert.seat.application.SeatService;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.user.application.UserService;
import com.hhplus.concert.userCharge.application.UserChargeService;
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
    private TokenService tokenService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ConcertItemService concertItemService;


    public RegistrationResponse reservation(RegistrationRequest request) {
        ConcertReservationEntity reservation = concertReservationService.reservation(request);
        SeatEntity seat = seatService.getSeat(reservation.getSeatId());
        ConcertEntity concert = concertService.getConcert(request.getConcertId());


        return new RegistrationResponse(concert.getConcertId(), seat.getSeatId(), LocalDateTime.now());
    }

    public PaymentResponse pay(Long concertId, PaymentRequest request) {
        userChargeService.checkValidMoney(request.getUserId(), request.getPrice());
        concertReservationService.checkRegistrationTime(concertId, request.getUserId());
        userChargeService.pay(concertId, request);
        userService.getUser(request.getUserId());

        ConcertEntity concert = concertService.getConcert(concertId);

        LocalDateTime now = LocalDateTime.now();
        return new PaymentResponse(request.getUserId(), concert, now);
    }
}
