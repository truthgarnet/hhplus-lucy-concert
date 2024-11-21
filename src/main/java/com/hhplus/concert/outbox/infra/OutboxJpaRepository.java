package com.hhplus.concert.outbox.infra;

import com.hhplus.concert.concertSeat.infra.ConcertSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(String status);
}
