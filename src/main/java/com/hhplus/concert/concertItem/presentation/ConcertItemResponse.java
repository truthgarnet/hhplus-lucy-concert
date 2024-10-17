package com.hhplus.concert.concertItem.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConcertItemResponse {

    private Long concertId;
    private List<Long> seat;

}
