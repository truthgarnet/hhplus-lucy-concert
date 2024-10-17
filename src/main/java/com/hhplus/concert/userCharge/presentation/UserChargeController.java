package com.hhplus.concert.userCharge.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "balance", description = "사용자의 잔액에 관한 API입니다.")
@RestController
@RequestMapping(value = "balance")
public class UserChargeController {

    @Operation(summary = "결제에 사용될 금액을 충전합니다.")
    @PostMapping("/charge")
    public ChargeResponse charge(@RequestBody ChargeRequest chargeRequest) {
        LocalDateTime now = LocalDateTime.now();
        return new ChargeResponse(1000, 1000, now);
    }

    @Operation(summary = "사용자의 잔액을 조회합니다.")
    @GetMapping("/{userId}")
    public UserChargeResponse getUserAmount(@PathVariable("userId") Long userId) {
        return new UserChargeResponse(1000);
    }

}
