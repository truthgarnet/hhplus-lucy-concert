package com.hhplus.concert.concertItem.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchSeatResponse {

    private LocalDate searchDate;

    private ConcertItemResponse concertResponse;


}
