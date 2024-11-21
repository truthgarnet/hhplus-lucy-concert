package com.hhplus.concert.common.util;

import com.hhplus.concert.outbox.infra.OutboxEntity;
import com.hhplus.concert.outbox.infra.OutboxJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class Scheduler {

    @Autowired
    private OutboxJpaRepository outboxJpaRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedRate = 1000)
    public void publishMessages() {
        List<OutboxEntity> messages = outboxJpaRepository.findByStatus("PENDING");

        for (OutboxEntity message : messages) {
            try {
                kafkaTemplate.send("order-events", message.getPayload());

                message.setStatus("PROCESSED");
                outboxJpaRepository.save(message);
            } catch (Exception e) {
                message.setStatus("FAILED");
                outboxJpaRepository.save(message);

                log.error("Failed to publish message: " + message.getId(), e);
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void retryFailedMessages() {
        List<OutboxEntity> failedMessages = outboxJpaRepository.findByStatus("FAILED");

        for (OutboxEntity message : failedMessages) {
            try {
                kafkaTemplate.send("order-events", message.getPayload());

                message.setStatus("PROCESSED");
                outboxJpaRepository.save(message);
            } catch (Exception e) {
                log.error("Retry failed for message: " + message.getId(), e);
            }
        }
    }

}
