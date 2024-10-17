package com.hhplus.concert.unit;

import com.hhplus.concert.common.exception.CustomException;
import com.hhplus.concert.common.exception.ErrorCode;
import com.hhplus.concert.common.util.TokenUtil;
import com.hhplus.concert.token.application.ProgressStatus;
import com.hhplus.concert.token.application.TokenService;
import com.hhplus.concert.token.infra.TokenEntity;
import com.hhplus.concert.token.infra.TokenJpaRepository;
import com.hhplus.concert.token.presentation.TokenResponse;
import com.hhplus.concert.token.presentation.WaitingQueResponse;
import com.hhplus.concert.user.application.UserService;
import com.hhplus.concert.user.infra.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TokenUnitTest {

    @Mock
    private TokenJpaRepository tokenJpaRepository;

    @Mock
    private UserService userService;

    @Mock
    private TokenUtil tokenUtil;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("다음 대기큐가 없을 경우 wait 상태입니다.")
    void testCreateToken_whenEmptyWaitingQueue() {
        // given
        String userId = "user1";
        String userName = "userName";

        UserEntity mockUser = new UserEntity(userId, userName);
        String token = "token";

        when(userService.createUser(userId)).thenReturn(mockUser);
        when(tokenJpaRepository.findTop1ByOrderByTokenIdDesc()).thenReturn(null);
        when(tokenUtil.generateToken()).thenReturn(token);

        // when
        TokenResponse response = tokenService.createToken(userId);

        assertNotNull(response);
        assertEquals(token, response.getToken());
        assertEquals(ProgressStatus.WAIT, response.getStatus());
    }

    @Test
    @DisplayName("대기큐가 존재할 때 토큰 생성합니다.")
    void testCreateToken_whenExistingWaitingQueue() {
        // given
        String userId = "user2";
        String userName = "userName";

        UserEntity mockUser = new UserEntity(userId, userName);
        String token = "token";

        when(userService.createUser(userId)).thenReturn(mockUser);
        TokenEntity mockToken = new TokenEntity(userId, "token", 1L, ProgressStatus.ONGOING, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
        when(tokenJpaRepository.findTop1ByOrderByTokenIdDesc()).thenReturn(mockToken);
        when(tokenUtil.generateToken()).thenReturn(token);

        // when
        TokenResponse response = tokenService.createToken(userId);

        assertNotNull(response);
        assertEquals(token, response.getToken());
        assertEquals(ProgressStatus.ONGOING, response.getStatus());
    }
    
    @Test
    @DisplayName("대기열 정보 가져오기")
    void testGetToken_whenSuccess() {
        // given
        String userId = "user1";
        String token = "token";
        Long waitingQue = 1L;
        ProgressStatus status = ProgressStatus.WAIT;
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime expiredDate = LocalDateTime.now().plusMinutes(5);

        TokenEntity mockToken = new TokenEntity(1L, userId, token, waitingQue, status, createdDate, expiredDate);
        when(tokenJpaRepository.findByToken(token)).thenReturn(Optional.of(mockToken));
        when(tokenJpaRepository.countByStatus(status)).thenReturn(1);

        // when
        WaitingQueResponse response = tokenService.getToken(mockToken.getToken());

        assertNotNull(response);
        assertEquals(ProgressStatus.WAIT, response.getStatus());
        assertEquals(1, response.getWaiting());
    }

    @Test
    @DisplayName("존재하지 않는 토큰 테스트")
    void testGetToken_whenFailed() {
        // given
        String invalidToken = "token";
        when(tokenJpaRepository.findByToken(invalidToken)).thenReturn(Optional.empty());

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            tokenService.getToken(invalidToken);
        });

        // then
        assertEquals(ErrorCode.TOKEN_ERROR, exception.getErrorCode());

        verify(tokenJpaRepository).findByToken(invalidToken);
    }
}
