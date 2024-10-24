package com.hhplus.concert.unit;

import com.hhplus.concert.concert.application.ConcertFacade;
import com.hhplus.concert.concert.application.ConcertService;
import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concertReservation.application.ConcertReservationService;
import com.hhplus.concert.concertReservation.presentation.PaymentRequest;
import com.hhplus.concert.concertReservation.presentation.PaymentResponse;
import com.hhplus.concert.user.application.UserService;
import com.hhplus.concert.user.infra.UserEntity;
import com.hhplus.concert.userCharge.application.UserChargeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ChargeFacadeUnitTest {

    @Mock
    private UserChargeService userChargeService;

    @Mock
    private ConcertReservationService concertReservationService;

    @Mock
    private UserService userService;

    @Mock
    private ConcertService concertService;

    @InjectMocks
    private ConcertFacade concertFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("결제 테스트")
    public void testPay() {
        // Given: 테스트를 위한 데이터 설정
        Long concertId = 1L;
        String userId = "user";
        Long seatId = 1L;
        String token = "token";
        int price = 50000;
        PaymentRequest request = new PaymentRequest(userId, concertId, price);

        // Mock 콘서트와 사용자 정보 설정
        ConcertEntity mockConcert = new ConcertEntity(concertId, "루시", "상암동", 120000, 1L);
        UserEntity user = new UserEntity(userId, "팬");

        // Mocking 각 서비스의 동작 설정
        doNothing().when(userChargeService).checkValidMoney(userId, price);
        doNothing().when(concertReservationService).checkRegistrationTime(concertId, userId);
        doNothing().when(userChargeService).pay(concertId, request);
        when(userService.getUser(userId)).thenReturn(user);
        when(concertService.getConcert(concertId)).thenReturn(mockConcert);

        // When: 메소드 실행
        PaymentResponse response = concertFacade.pay(concertId, request);

        // Then: 결과 검증
        assertNotNull(response);
        assertEquals(userId, response.getUserId());
        assertEquals(concertId, response.getConcert().getConcertId());

        // 각 서비스가 한 번씩 호출되었는지 검증
        verify(userChargeService, times(1)).checkValidMoney(userId, price);
        verify(concertReservationService, times(1)).checkRegistrationTime(concertId, userId);
        verify(userChargeService, times(1)).pay(concertId, request);
        verify(userService, times(1)).getUser(userId);
        verify(concertService, times(1)).getConcert(concertId);
    }
}
