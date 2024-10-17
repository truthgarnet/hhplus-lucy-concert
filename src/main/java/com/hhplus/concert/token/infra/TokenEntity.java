package com.hhplus.concert.token.infra;

import com.hhplus.concert.token.application.ProgressStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String userId;
    private String token;
    private Long waitingQue;
    private ProgressStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime expiredDate;

    public TokenEntity(String userId, String token, Long waitingQue, ProgressStatus status, LocalDateTime createdDate, LocalDateTime expiredDate) {
        this.userId = userId;
        this.token = token;
        this.waitingQue = waitingQue;
        this.status = status;
        this.createdDate = createdDate;
        this.expiredDate = expiredDate;
    }

    public TokenEntity(String userId, String token, Long waitingQue, ProgressStatus status, LocalDateTime createdDate) {
        this.userId = userId;
        this.token = token;
        this.waitingQue = waitingQue;
        this.status = status;
        this.createdDate = createdDate;
    }
}
