package com.hhplus.concert.token.infra;

import com.hhplus.concert.token.application.ProgressStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {

    TokenEntity findByUserId(String userId);

    Optional<TokenEntity> findByToken(String token);

    @Query("SELECT MAX(t.tokenId) FROM TokenEntity t WHERE t.status = :status")
    Long findNextPriorityWaitNo(@Param("status") ProgressStatus status);

    TokenEntity findTop1ByOrderByTokenIdDesc();

    int countByStatus(ProgressStatus progressStatus);

    @Query(value = "UPDATE token SET status='expire' WHERE expire_date < CURRENT_TIMESTAMP ", nativeQuery = true)
    void updateExpireToken();

    @Query(value = "UPDATE token SET expire_date=:expire_date AND status=:status WHERE expire_date < CURRENT_TIMESTAMP ", nativeQuery = true)
    void updateExpiredDateAndStatus(@Param("expire_date") LocalDateTime expire_date, @Param("status") ProgressStatus status);
}
