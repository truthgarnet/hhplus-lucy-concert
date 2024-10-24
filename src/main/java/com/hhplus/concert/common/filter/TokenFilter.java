package com.hhplus.concert.common.filter;

import com.hhplus.concert.common.exception.TokenErrorException;
import com.hhplus.concert.common.exception.TokenExpiredException;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.token.application.TokenStatus;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    private final String[] concertUris = new String[]{"/user/waitingQue/**","/concert/**", "/balance/**"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 토큰 헤더에서 가져오기
        String token = getBearerToken(request);
        
        // 2. 토큰을 체크 해야 하는 uri인지 확인
        if (concertUriCheck(request.getRequestURI())) {
            // 3. 토큰 체크 하기
            if (token == null) {
                throw new TokenErrorException("토큰이 없습니다.");
            } else {
                TokenStatus tokenStatus = tokenService.getStatus(token);
                if (tokenStatus == TokenStatus.INVALID) {
                    throw new TokenErrorException("토큰이 존재하지 않습니다.");
                }

                if (tokenStatus == TokenStatus.EXPIRED) {
                    throw new TokenExpiredException("토큰이 만료되었습니다.");
                }
            }
        }
        filterChain.doFilter(request, response);

    }

    // Header에서 토큰을 가져옴
    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Concert api
    private boolean concertUriCheck(String uri) {
        return PatternMatchUtils.simpleMatch(concertUris, uri);
    }

}
