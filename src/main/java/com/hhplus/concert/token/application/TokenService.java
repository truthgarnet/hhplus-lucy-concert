package com.hhplus.concert.token.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.common.util.TokenUtil;
import com.hhplus.concert.token.infra.TokenEntity;
import com.hhplus.concert.token.infra.TokenJpaRepository;
import com.hhplus.concert.token.presentation.TokenResponse;
import com.hhplus.concert.token.presentation.WaitingQueResponse;
import com.hhplus.concert.user.application.UserService;
import com.hhplus.concert.user.infra.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private TokenJpaRepository tokenJpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Transactional
    public TokenResponse createToken(String userId) {
        // 0-1. 사용자 생성
        UserEntity user = userService.createUser(userId);

        // 0-2. 대기열 순번 조회
        Long waitingQue = tokenJpaRepository.findNextPriorityWaitNo(ProgressStatus.WAIT);

        if (waitingQue == null) {
            waitingQue = 0L;
        }

        long nextWaitingQue = 0;
        if (waitingQue != 0) {
            nextWaitingQue  = waitingQue + 1;
        }

        // 1. 토큰 생성
        String generateToken = tokenUtil.generateToken();

        // 2. 상태
        ProgressStatus status = null;
        if (nextWaitingQue == 0) {
             status = ProgressStatus.WAIT;
        } else {
            status = tokenJpaRepository.findTop1ByOrderByTokenIdDesc().getStatus();
        }

        // 3. 토큰 저장
        TokenEntity token = null;
        if (status == ProgressStatus.WAIT) {
            token = new TokenEntity(userId, generateToken, nextWaitingQue, status, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
        } else if (status == ProgressStatus.ONGOING) {
            token = new TokenEntity(userId, generateToken, nextWaitingQue, status, LocalDateTime.now());
        }
        
        tokenJpaRepository.save(token);

        return TokenResponse.from(token);
    }

    public WaitingQueResponse getToken(String token) {
        // 토큰 순서: 성공 - 현재Id
        int successToken = tokenJpaRepository.countByStatus(ProgressStatus.SUCCESS);

        TokenEntity tokenEntity = tokenJpaRepository.findByToken(token).orElseThrow(() -> new CustomException(ErrorCode.TOKEN_ERROR));
        int currentWaitingQue = tokenEntity.getTokenId().intValue() - successToken;

        return new WaitingQueResponse(tokenEntity.getStatus(), currentWaitingQue);
    }

    public void checkValidToken(String token) {
        // 스케쥴러로 만료 처리하니까 토큰 상태 값으로만 체크하는 게 맞다?
        TokenEntity tokenEntity = tokenJpaRepository.findByToken(token).orElseThrow(() -> new CustomException(ErrorCode.TOKEN_ERROR));
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(tokenEntity.getExpiredDate())) {
            throw new CustomException(ErrorCode.TOKEN_ERROR);
        }
    }

    public void processToken() {

    }

    public void updateExpire() {
        // 토큰 만료 처리
        tokenJpaRepository.updateExpireToken();
    }
}
