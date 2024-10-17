package com.hhplus.concert.userCharge.infra;

import com.hhplus.concert.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_charge")
@Getter
public class UserChargeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userChargeId;

    private String userId;
    private int amount;

}
