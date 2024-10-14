package com.hhplus.concert.waitingQue.infra;

import com.hhplus.concert.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "waiting")
@Getter
public class WaitingQueEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long waitingId;
    private int status; // 0: wait, 1: success

}
