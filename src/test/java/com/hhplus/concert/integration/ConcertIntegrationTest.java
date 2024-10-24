package com.hhplus.concert.integration;

import com.hhplus.concert.concert.application.ConcertFacade;
import com.hhplus.concert.concertItem.infra.ConcertItemJpaRepository;
import com.hhplus.concert.concertReservation.application.ConcertReservationService;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConcertIntegrationTest {

    @Autowired
    private ConcertFacade concertFacade;

    @Test
    @Transactional
    @DisplayName("동시에 콘서트 예약을 신청합니다.")
    public void test_concertReservation() throws Exception {
        // Given
        String userId = "test";
        Long concertId = 1L;
        Long seatId = 1L;
        RegistrationRequest request = new RegistrationRequest(userId, concertId, seatId);

        // when
        ExecutorService executor = Executors.newFixedThreadPool(30);
        AtomicInteger failedOperations = new AtomicInteger(0); // 실패한 작업 수를 세기 위한 변수

        for (int i = 0; i < 40; i++) {
            executor.submit(() -> {
                try {
                    concertFacade.reservation(request);
                } catch (RuntimeException e) {
                    System.out.println("에러:" + e.getMessage());
                    failedOperations.incrementAndGet(); // 예외가 발생하면 실패 카운트 증가
                }
            });
        }

        // 스레드 종료 후 결과 확인
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS); // 모든 스레드가 종료될 때까지 대기
        // 실패 10번
        assertEquals(0, failedOperations.get(), "동시 다발적으로 콘서트 예약을 신청합니다.");
    }
}
