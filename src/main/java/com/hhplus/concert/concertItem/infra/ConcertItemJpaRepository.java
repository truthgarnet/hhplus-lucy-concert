package com.hhplus.concert.concertItem.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertItemJpaRepository extends JpaRepository<ConcertItemEntity, Long> {

    @Query(value = "SELECT * FROM concert_item WHERE :now BETWEEN reservation_start AND reservation_end", nativeQuery = true)
    List<ConcertItemEntity> findReservationsBetween(@Param(value = "now") LocalDate now);

    Optional<ConcertItemEntity> findByConcertId(Long concertId);
}
