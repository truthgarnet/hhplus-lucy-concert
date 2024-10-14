package com.hhplus.concert.userCharge.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChargeJpaRepository extends JpaRepository<UserChargeEntity, Long> {
}
