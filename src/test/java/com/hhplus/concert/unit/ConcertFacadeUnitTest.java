package com.hhplus.concert.unit;

import com.hhplus.concert.concert.application.ConcertFacade;
import com.hhplus.concert.concert.application.ConcertService;
import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concertItem.application.ConcertItemService;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertReservation.application.ConcertReservationService;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import com.hhplus.concert.concertReservation.presentation.RegistrationResponse;
import com.hhplus.concert.seat.application.SeatService;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.token.application.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ConcertFacadeUnitTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private ConcertReservationService concertReservationService;

    @Mock
    private ConcertItemService concertItemService;

    @Mock
    private SeatService seatService;

    @Mock
    private ConcertService concertService;

    @InjectMocks
    private ConcertFacade concertFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("좌석 예약 요청합니다.")
    public void testReservation_whenSuccess() {
        // Given
        String token = "token";
        String userId = "userId";
        Long concertId = 1L;
        Long concertItemId = 1L;
        Long seatId = 10L;
        List<Long> seatIds = Arrays.asList(1L, 2L, 3L, 5L);
        LocalDate reservationStart = LocalDate.now();
        LocalDate reservationEnd = LocalDate.now().plusDays(1);
        LocalDate concertStart = LocalDate.now().plusMonths(2);

        RegistrationRequest request = new RegistrationRequest(userId, concertId, seatId, token);

        // Mock 각 서비스의 동작
        ConcertReservationEntity reservation = new ConcertReservationEntity(userId, concertId, seatId, 0);
        ConcertItemEntity concertItem = new ConcertItemEntity(concertItemId, seatIds, reservationStart, reservationEnd, concertStart);
        SeatEntity seat = new SeatEntity(seatId, 50, 50);
        ConcertEntity concert = new ConcertEntity(concertId, "루시콘서트", "상암 실내 체육관", 156000, 1L);

        doNothing().when(tokenService).checkValidToken(token);  // tokenService의 메소드는 아무 작업도 하지 않도록 설정
        when(concertReservationService.reservation(request)).thenReturn(reservation);
        when(concertItemService.getConcertItem(concertId)).thenReturn(concertItem);
        when(seatService.getSeat(seatId)).thenReturn(seat);
        when(concertService.getConcert(concertId)).thenReturn(concert);
        // when(concertFacade.)

        // When: 파사드 메소드 호출
        RegistrationResponse response = concertFacade.reservation(request);

        // Then: 결과 검증
        assertNotNull(response);
        assertEquals(concertId, response.getConcertId());
        assertEquals(seatId, response.getSeatId());

        // 각 서비스가 한 번씩만 호출되었는지 검증
        verify(tokenService, times(1)).checkValidToken(token);
        verify(concertReservationService, times(1)).reservation(request);
        verify(concertItemService, times(1)).getConcertItem(concertId);
        verify(seatService, times(1)).getSeat(seatId);
        verify(concertService, times(1)).getConcert(concertItem.getConcertItemId());
    }

}
