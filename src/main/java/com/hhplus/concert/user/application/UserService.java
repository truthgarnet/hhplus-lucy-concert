package com.hhplus.concert.user.application;

import com.hhplus.concert.user.infra.UserEntity;
import com.hhplus.concert.user.infra.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public UserEntity createUser(String userId) {
        UserEntity user = userJpaRepository.findById(userId)
                    .orElse(userJpaRepository.save(new UserEntity(userId)));
        return user;
    }

}
