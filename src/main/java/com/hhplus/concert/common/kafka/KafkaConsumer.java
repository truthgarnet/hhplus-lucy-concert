package com.hhplus.concert.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic", groupId = "group")
    public void receiveMsg(String msg) {
        log.info("Received message: {}", msg);
    }
}
