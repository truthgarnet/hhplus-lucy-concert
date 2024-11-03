package com.hhplus.concert.user.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    @Modifying
    @Query(value = "UPDATE user SET token_id=:token_id", nativeQuery = true)
    void updateByTokenId(@Param("token_id") Long tokenId);
}
