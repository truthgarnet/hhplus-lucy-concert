package com.hhplus.concert.sms.application;

import com.hhplus.concert.common.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendRegistration(String userId) {
        try {
            kafkaProducer.sendMsg("topic", userId);

        } catch (Exception e) {
            log.error("Thread Sleep Error");
        }

    }
}
