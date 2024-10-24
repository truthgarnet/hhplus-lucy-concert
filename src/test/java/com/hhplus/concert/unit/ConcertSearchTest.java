package com.hhplus.concert.unit;

import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concert.infra.ConcertJpaRepository;
import com.hhplus.concert.concertItem.application.ConcertItemService;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertItem.infra.ConcertItemJpaRepository;
import com.hhplus.concert.concertItem.presentation.ConcertItemResponse;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.seat.infra.SeatJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ConcertSearchTest {

    @Mock
    private ConcertItemJpaRepository concertItemJpaRepository;

    @Mock
    private ConcertJpaRepository concertJpaRepository;

    @Mock
    private SeatJpaRepository seatJpaRepository;

    @InjectMocks
    private ConcertItemService concertItemService;

    private LocalDate now;
    private LocalDate concertStart;
    private Long seat1Id = 101L;
    private Long seat2Id = 102L;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        now = LocalDate.now();
        concertStart = now.plusMonths(2);
    }

    @Test
    @DisplayName("예약 가능 날짜 검색 하기")
    public void testGetAvailableDate() {
        // Given
        LocalDate concertStart = LocalDate.now().plusMonths(2);
        List<ConcertItemEntity> mockConcertItems = Arrays.asList(
                new ConcertItemEntity(1L, seat1Id, now, now.plusDays(2), concertStart),
                new ConcertItemEntity(2L, seat2Id, now.plusDays(1), now.plusDays(10), concertStart)
        );

        // When
        when(concertItemJpaRepository.findReservationsBetween(now)).thenReturn(mockConcertItems);

        // Act
        List<ConcertItemEntity> result = concertItemService.getAvailableDate();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(concertItemJpaRepository, times(1)).findReservationsBetween(now);
    }

    @Test
    @DisplayName("날짜 정보를 입력 받아 예약 가능한 좌석 정보를 조회합니다.")
    public void testGetAvailableSeat() {

        Long concertId = 1L;

        List<ConcertItemEntity> mockConcertItems = Arrays.asList(
                new ConcertItemEntity(seat1Id, seat1Id, now.plusDays(2), now.plusDays(2), concertStart),
                new ConcertItemEntity(seat2Id, seat2Id, now.minusDays(1), now.plusDays(10), concertStart)
        );

        String concertLocation = "상암동";
        ConcertEntity concertEntity1 = new ConcertEntity(1L, "루시 열콘서트", concertLocation, 100000, 1L);
        ConcertEntity concertEntity2 = new ConcertEntity(2L, "루시 떼굴뗴굴", concertLocation, 200000, 2L);

        List<SeatEntity> seatEntities1 = Arrays.asList(new SeatEntity(1L, 50, 50, 1L), new SeatEntity(2L, 50, 50, 2L));
        List<SeatEntity> seatEntities2 = Arrays.asList(new SeatEntity(3L, 50, 50, 3L), new SeatEntity(4L, 50, 50, 4l));

        // Mocking the repository calls
        when(concertItemJpaRepository.findReservationsBetween(any(LocalDate.class))).thenReturn(mockConcertItems);
        when(concertJpaRepository.findByConcertItemId(1L)).thenReturn(Optional.ofNullable((concertEntity1)));
        when(concertJpaRepository.findByConcertItemId(2L)).thenReturn(Optional.ofNullable((concertEntity2)));
        when(seatJpaRepository.findByConcertItemId(1L)).thenReturn(seatEntities1);
        when(seatJpaRepository.findByConcertItemId(2L)).thenReturn(seatEntities2);

        // Act
        List<ConcertItemResponse> result = concertItemService.getAvailableSeat(now);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify the first response
        assertEquals(1L, result.get(0).getConcertId());
        assertEquals(2, result.get(0).getSeat().size());
        assertTrue(result.get(0).getSeat().containsAll(Arrays.asList(1L, 2L)));

        // Verify the second response
        assertEquals(2L, result.get(1).getConcertId());
        assertEquals(2, result.get(1).getSeat().size());
        assertTrue(result.get(1).getSeat().containsAll(Arrays.asList(3L, 4L)));

        // Verifying repository interactions
        verify(concertItemJpaRepository, times(1)).findReservationsBetween(now);
        verify(concertJpaRepository, times(1)).findByConcertItemId(1L);
        verify(concertJpaRepository, times(1)).findByConcertItemId(2L);
        verify(seatJpaRepository, times(1)).findByConcertItemId(1L);
        verify(seatJpaRepository, times(1)).findByConcertItemId(2L);
    }

}
