package com.hhplus.concert.userCharge.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertItem.infra.ConcertItemJpaRepository;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.concertReservation.infra.ConcertReservationJpaRepository;
import com.hhplus.concert.concertReservation.presentation.PaymentRequest;
import com.hhplus.concert.concertReservation.presentation.PaymentResponse;
import com.hhplus.concert.userCharge.infra.UserChargeEntity;
import com.hhplus.concert.userCharge.infra.UserChargeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChargeService {

    @Autowired
    private UserChargeJpaRepository userChargeJpaRepository;

    @Autowired
    private ConcertReservationJpaRepository concertReservationJpaRepository;

    @Autowired
    private ConcertItemJpaRepository concertItemJpaRepository;

    public void checkValidMoney(String userId, int pay) {
        UserChargeEntity userCharge = userChargeJpaRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ErrorCode.MONEY_ERROR));
        if (userCharge.getAmount() < pay) {
            throw new CustomException(ErrorCode.MONEY_ERROR);
        }
    }

    public void pay(Long concertId, PaymentRequest paymentRequest) {
        UserChargeEntity userCharge = userChargeJpaRepository.findByUserId(paymentRequest.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.DATABASE_ERROR));

        userChargeJpaRepository.updateUserPayment(paymentRequest.getPrice(), paymentRequest.getUserId(), concertId, paymentRequest.getSeatId());
    }
}
