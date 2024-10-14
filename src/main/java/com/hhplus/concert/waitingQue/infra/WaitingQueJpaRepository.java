package com.hhplus.concert.waitingQue.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingQueJpaRepository extends JpaRepository<WaitingQueEntity, Long> {

}
