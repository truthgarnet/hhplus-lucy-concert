package com.hhplus.concert.concert.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {

    Optional<ConcertEntity> findByConcertItemId(Long concertItemId);
}
