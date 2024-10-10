package com.hhplus.concert.controller;

import com.hhplus.concert.domain.Concert;
import com.hhplus.concert.domain.User;
import com.hhplus.concert.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "concert", description = "콘서트에 관한 API입니다.")
@RestController
@RequestMapping(value = "concert")
public class ConcertController {

    @Operation(summary = "예약 가능 날짜 검색합니다.")
    @GetMapping("search/date")
    public SearchDateResponse searchAvailableDate(@RequestParam("date") String date) {
        List<Concert> concertList = new ArrayList<>();
        Concert concert1 = new Concert(1L, "루시의 낙화 콘서트", "고척돔", 100000);
        Concert concert2 = new Concert(2L, "루시의 떼굴떼굴", "고척돔", 2500000);
        concertList.add(concert1);
        concertList.add(concert2);

        return new SearchDateResponse(concertList);
    }

    @Operation(summary = "날짜 정보를 입력받아 예약가능한 좌석정보를 조회합니다.")
    @GetMapping("search/seat")
    public SearchSeatResponse searchSeat(@RequestParam("date") String date) {
        List<Integer> availableSeats = Arrays.asList(1, 2, 5, 7, 8);
        return new SearchSeatResponse(availableSeats);
    }

    @Operation(summary = "좌석 예약 요청합니다.")
    @PostMapping("registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest registrationRequest) {
        LocalDateTime now = LocalDateTime.now();
        return new RegistrationResponse(1L, 20L, now);
    }

    @Operation(summary = "콘서트 결제합니다.")
    @PostMapping("/payment/{concertId}")
    public PaymentResponse paymentConcert(@PathVariable Long concertId, @RequestBody PaymentRequest paymentRequest) {
        User user = new User(1L, "테스트");
        Concert concert = new Concert(concertId, "루시콘서트", "고척돔", 100000);
        LocalDateTime now = LocalDateTime.now();
        return new PaymentResponse(user, concert, now);
    }

}
