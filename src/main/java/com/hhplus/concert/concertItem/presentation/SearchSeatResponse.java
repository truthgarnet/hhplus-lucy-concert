package com.hhplus.concert.concertItem.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchSeatResponse {

    private List<Integer> seatAvailable;

}
