package com.hhplus.concert.domain;

public record Concert (Long concertId, String concertName, String concertLocation,
                       int fee) {

}
