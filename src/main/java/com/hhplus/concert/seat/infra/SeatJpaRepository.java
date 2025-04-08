package com.hhplus.concert.seat.infra;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findByConcertItemId(Long concertItemId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SeatEntity> findBySeatId(Long SeatId);

}
