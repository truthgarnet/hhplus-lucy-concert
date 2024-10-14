package com.hhplus.concert.token.infra;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "token")
@Getter
public class TokenEntity {

    @Id
    private String tokenId;

    private Long userId;
    private Long waitingId;

}
