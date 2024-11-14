package com.hhplus.concert.sms.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SmsService {

    /*
        예시로 만들어 낸 외부 API
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendRegistration(String userId) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            log.error("Thread Sleep Error");
        }

    }
}
