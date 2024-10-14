package com.hhplus.concert.seat.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {
}
