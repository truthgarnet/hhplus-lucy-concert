package com.hhplus.concert.concertItem.presentation;

import com.hhplus.concert.concert.Concert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchDateResponse {

    private List<Concert> concerts;

}
