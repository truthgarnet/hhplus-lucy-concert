package com.hhplus.concert.user.presentation;

import com.hhplus.concert.common.response.CommonResponse;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.token.presentation.TokenRequest;
import com.hhplus.concert.token.presentation.TokenResponse;
import com.hhplus.concert.token.presentation.WaitingQueResponse;
import com.hhplus.concert.user.application.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "사용자에 관한 API입니다.")
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "대기열 토큰을 생성합니다.")
    @PostMapping("/token")
    public ResponseEntity<CommonResponse<Object>> token(@RequestBody String userId) {
        TokenResponse tokenResponse = tokenService.createToken(userId);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("대기열 토큰을 생성했습니다.")
                .data(tokenResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "본인의 대기번호를 조회합니다.")
    @GetMapping("/waitingQue")
    public ResponseEntity<CommonResponse<Object>> getWaitingQue(@RequestHeader("Authorization") String token) {
        WaitingQueResponse tokenResponse = tokenService.getToken(token);

        CommonResponse<Object> response = CommonResponse.builder()
                .msg("대기열 토큰을 조회했습니다.")
                .data(tokenResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
