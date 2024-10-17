package com.hhplus.concert.concert.presentation;

import com.hhplus.concert.common.response.CommonResponse;
import com.hhplus.concert.concert.application.ConcertFacade;
import com.hhplus.concert.concertItem.application.ConcertItemService;
import com.hhplus.concert.concertItem.infra.ConcertItemEntity;
import com.hhplus.concert.concertItem.presentation.ConcertItemResponse;
import com.hhplus.concert.concertReservation.presentation.PaymentRequest;
import com.hhplus.concert.concertReservation.presentation.PaymentResponse;
import com.hhplus.concert.concertReservation.presentation.RegistrationRequest;
import com.hhplus.concert.concertReservation.presentation.RegistrationResponse;
import com.hhplus.concert.userCharge.application.UserChargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "concert", description = "콘서트에 관한 API입니다.")
@RestController
@RequestMapping(value = "concert")
public class ConcertController {

    @Autowired
    private ConcertItemService concertItemService;

    @Autowired
    private ConcertFacade concertFacade;

    @Autowired
    private UserChargeService userChargeService;

    @Operation(summary = "예약 가능 날짜 검색합니다.")
    @GetMapping("search/date")
    public ResponseEntity<CommonResponse<Object>> searchAvailableDate(@RequestParam("date") String date) {
        List<ConcertItemEntity> concertList = concertItemService.getAvailableDate();

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("예약 가능 날짜를 검색했습니다.")
                .data(concertList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "날짜 정보를 입력 받아 예약 가능한 좌석 정보를 조회합니다.")
    @GetMapping("search/seat")
    public ResponseEntity<CommonResponse<Object>> searchSeat(@RequestParam("date") LocalDate date) {
        List<ConcertItemResponse> seatList = concertItemService.getAvailableSeat(date);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("예약 가능한 좌석을 검색했습니다.")
                .data(seatList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "좌석 예약 요청합니다.")
    @PostMapping("registration")
    public  ResponseEntity<CommonResponse<Object>> registration(@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse registration = concertFacade.reservation(registrationRequest);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("좌석 예약을 성공했습니다.")
                .data(registration)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "콘서트 결제합니다.")
    @PostMapping("/payment/{concertId}")
    public ResponseEntity<CommonResponse<Object>> paymentConcert(@PathVariable Long concertId, @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse payment = concertFacade.pay(concertId, paymentRequest);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("콘서트 결제를 성공했습니다.")
                .data(payment)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
