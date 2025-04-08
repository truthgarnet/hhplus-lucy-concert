package com.hhplus.concert.concertItem.application;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.concert.infra.ConcertEntity;
import com.hhplus.concert.concert.infra.ConcertJpaRepository;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertItem.infra.ConcertItemJpaRepository;
import com.hhplus.concert.concertItem.presentation.ConcertItemResponse;
import com.hhplus.concert.seat.infra.SeatEntity;
import com.hhplus.concert.seat.infra.SeatJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertItemService {

    @Autowired
    private ConcertJpaRepository concertJpaRepository;

    @Autowired
    private ConcertItemJpaRepository concertItemJpaRepository;

    @Autowired
    private SeatJpaRepository seatJpaRepository;

    public List<ConcertItemEntity> getAvailableDate() {
        return concertItemJpaRepository.findReservationsBetween(LocalDate.now());
    }

    public List<ConcertItemResponse> getAvailableSeat(LocalDate date) {
        // 해당 날짜
        List<ConcertItemEntity> concertItem = concertItemJpaRepository.findReservationsBetween(date);
        List<ConcertItemResponse> concertItemResponses = new ArrayList<>();
        List<Long> seats = null;
        for (int i = 0; i < concertItem.size(); i++) {

            ConcertItemEntity concertItemEntity = concertItem.get(i);
            Long concertItemId = concertItemEntity.getConcertItemId();
            ConcertEntity concertEntity = concertJpaRepository.findByConcertItemId(concertItemId).orElseThrow(() -> new CustomException(ErrorCode.DATABASE_ERROR));

            List<SeatEntity> seatEntity = seatJpaRepository.findByConcertItemId(concertItemEntity.getConcertItemId());
            seats = new ArrayList<>();
            for (int j = 0; j < seatEntity.size(); j++) {
                seats.add(seatEntity.get(j).getSeatId());
            }
            concertItemResponses.add(new ConcertItemResponse(concertEntity.getConcertId(), seats));
        }

        return concertItemResponses;
    }

}
