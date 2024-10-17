package com.hhplus.concert.concertReservation.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertReservationJpaRepository extends JpaRepository<ConcertReservationEntity, Long> {
    Optional<ConcertReservationEntity> findByConcertIdAndUserId(Long concertId, String userId);
}
