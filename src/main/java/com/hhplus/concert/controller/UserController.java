package com.hhplus.concert.controller;

import com.hhplus.concert.dto.TokenRequest;
import com.hhplus.concert.dto.TokenResponse;
import com.hhplus.concert.dto.WaitingQueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "user", description = "사용자에 관한 API입니다.")
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Operation(summary = "대기열 토큰을 생성합니다.")
    @PostMapping("/token")
    public TokenResponse token(@RequestBody TokenRequest tokenRequest) {
        LocalDateTime date = LocalDateTime.now();
        date.plusMinutes(10);

        return new TokenResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.eaBjZPHZMZ2xTqPy_JPexYz0gZPAmvFCqffvjJDluZ8", date);
    }

    @Operation(summary = "본인의 대기번호를 조회합니다.")
    @GetMapping("/waitingQue")
    public WaitingQueResponse getWaitingQue(@RequestParam("token") String token) {
        return new WaitingQueResponse(1L, 3);
    }


}
