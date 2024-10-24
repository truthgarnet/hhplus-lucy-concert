package com.hhplus.concert.userCharge.presentation;

import com.hhplus.concert.common.response.CommonResponse;
import com.hhplus.concert.userCharge.application.UserChargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "balance", description = "사용자의 잔액에 관한 API입니다.")
@RestController
@RequestMapping(value = "balance")
public class UserChargeController {

    @Autowired
    private UserChargeService userChargeService;

    @Operation(summary = "결제에 사용될 금액을 충전합니다.")
    @PostMapping("/charge")
    public ResponseEntity<CommonResponse<Object>> charge(@RequestBody ChargeRequest chargeRequest) {
        ChargeResponse chargeResponse = userChargeService.charge(chargeRequest);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("충전을 완료 했습니다.")
                .data(chargeResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "사용자의 잔액을 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<Object>> getUserAmount(@PathVariable("userId") String userId) {
        UserChargeResponse chargeResponse = userChargeService.getUserAmount(userId);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("잔액 조회를 완료 했습니다.")
                .data(chargeResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
