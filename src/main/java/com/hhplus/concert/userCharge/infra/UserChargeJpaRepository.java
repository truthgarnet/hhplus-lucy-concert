package com.hhplus.concert.userCharge.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserChargeJpaRepository extends JpaRepository<UserChargeEntity, Long> {

    Optional<UserChargeEntity> findByUserId(String userId);

    @Modifying
    @Query(value = "UPDATE user_charge SET amount=:amount WHERE user_id=:user_id AND concert_id=:concert_id AND seat_id=:seat_id", nativeQuery = true)
    void updateUserPayment(@Param("amount") int amount, @Param("user_id") String user_id, @Param("concert_id") Long concertId, @Param("seat_id") Long seat_id);
}
