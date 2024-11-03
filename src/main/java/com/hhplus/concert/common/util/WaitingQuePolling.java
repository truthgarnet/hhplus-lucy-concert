package com.hhplus.concert.common.util;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.token.application.ProgressStatus;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.token.infra.TokenEntity;
import com.hhplus.concert.token.infra.TokenJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WaitingQuePolling {

    private final TokenJpaRepository tokenJpaRepository;
    private TokenService tokenService;

    public WaitingQuePolling(TokenJpaRepository tokenJpaRepository) {
        this.tokenJpaRepository = tokenJpaRepository;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    @Transactional
    public void updateToken() {

        long ongoingCnt = tokenJpaRepository.countByStatus(ProgressStatus.ONGOING);
        long waitCnt = tokenJpaRepository.countByStatus(ProgressStatus.WAIT);


        if(ongoingCnt < 50) {
            int availableCount = (int) (50 - ongoingCnt);

            for(int i = 0; i<Math.min(availableCount, waitCnt); i++) {
                Long nextWaitNo = tokenJpaRepository.findNextPriorityWaitNo(ProgressStatus.WAIT); // 가장 우선인 다음 대기순번 고객

                TokenEntity token = tokenJpaRepository.findById(nextWaitNo).orElseThrow(() -> new CustomException(ErrorCode.ERROR));

                if(token != null) {
                    // 토큰의 속성을 변경하고 저장하는 부분
                    tokenJpaRepository.updateExpiredDateAndStatus(LocalDateTime.now().plusMinutes(10)
                            , ProgressStatus.ONGOING);
                }
            }
        }
    }


    // 5분
    @Scheduled(cron = "0 0/5 * * * *")
    @Transactional
    public void getExpireToken() {
        tokenService.updateExpire();
    }
}
