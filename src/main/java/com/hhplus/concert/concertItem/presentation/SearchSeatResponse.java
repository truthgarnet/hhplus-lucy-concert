package com.hhplus.concert.concertItem.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchSeatResponse {

    private LocalDate searchDate;

    private ConcertItemResponse concertResponse;


}
