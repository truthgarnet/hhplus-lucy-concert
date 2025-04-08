package com.hhplus.concert.userCharge.infra;

import com.hhplus.concert.common.BaseTimeEntity;
import com.hhplus.concert.userCharge.presentation.ChargeResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_charge")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserChargeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userChargeId;

    private String userId;
    private int amount;

    public UserChargeEntity(String userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
