package com.hhplus.concert.concertItem.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertItemJpaRepository extends JpaRepository<ConcertItemEntity, Long> {

}
