package com.hhplus.concert.concertReservation.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertReservationJpaRepository extends JpaRepository<ConcertReservationEntity, Long> {
}
