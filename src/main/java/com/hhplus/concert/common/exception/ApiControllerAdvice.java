package com.hhplus.concert.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMsg()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Map<String, Object>> handleTokenExpiredException(TokenExpiredException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "토큰 만료");
        response.put("code", 500);
        response.put("msg", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}

