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
import com.hhplus.concert.userCharge.presentation.ChargeRequest;
import com.hhplus.concert.userCharge.presentation.ChargeResponse;
import com.hhplus.concert.userCharge.presentation.UserChargeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserChargeService {

    @Autowired
    private UserChargeJpaRepository userChargeJpaRepository;

    @Autowired
    private ConcertReservationJpaRepository concertReservationJpaRepository;

    @Autowired
    private ConcertItemJpaRepository concertItemJpaRepository;

    public UserChargeEntity checkValidMoney(String userId, int pay) {
        UserChargeEntity userCharge = userChargeJpaRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ErrorCode.MONEY_ERROR));
        if (userCharge.getAmount() < pay) {
            throw new CustomException(ErrorCode.MONEY_ERROR);
        }
        return userCharge;
    }

    public void pay(Long concertId, PaymentRequest paymentRequest) {
        String userId = paymentRequest.getUserId();
        int pay = paymentRequest.getPrice();
        UserChargeEntity userCharge = checkValidMoney(userId, pay);
        int totalAmount = userCharge.getAmount() - pay;

        userChargeJpaRepository.updateUserPayment(userId, totalAmount);
    }

    public ChargeResponse charge(ChargeRequest chargeRequest) {
        String userId = chargeRequest.getUserId();
        UserChargeEntity userCharge = userChargeJpaRepository.findByUserId(userId).orElse(null);

        if (chargeRequest.getAmount() <= 0) {
            throw new CustomException(ErrorCode.CHARGE_ERROR);
        }

        if (userCharge == null) {
            int totalAmount = chargeRequest.getAmount();
            UserChargeEntity saveCharge = new UserChargeEntity(userId, totalAmount);
            userCharge = userChargeJpaRepository.save(saveCharge);
        } else {
            try {
                int totalAmount = userCharge.getAmount() + chargeRequest.getAmount();
                userChargeJpaRepository.updateUserPayment(userId, totalAmount);
            } catch (Exception e) {
                throw new CustomException(ErrorCode.DATABASE_ERROR);
            }
        }

        return new ChargeResponse(chargeRequest.getAmount(), userCharge.getAmount(), LocalDateTime.now());
    }

    public UserChargeResponse getUserAmount(String userId) {
        UserChargeEntity userCharge = userChargeJpaRepository.findByUserId(userId).orElse(new UserChargeEntity(userId, 0));
        UserChargeResponse userChargeResponse = new UserChargeResponse(userId, userCharge.getAmount());
        return userChargeResponse;
    }

}
