package com.hhplus.concert.dto;

import com.hhplus.concert.domain.Concert;
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
