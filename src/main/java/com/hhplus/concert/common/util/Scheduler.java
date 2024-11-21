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
                // 1. 카프카로 메시지 발행
                kafkaTemplate.send("order-events", message.getPayload());

                // 2. 발행 성공 시 상태 업데이트
                message.setStatus("PROCESSED");
                outboxJpaRepository.save(message);
            } catch (Exception e) {
                // 발행 실패 시 로그 기록 (재시도는 다음 스케줄에서)
                log.error("Failed to publish message: " + message.getId(), e);
            }
        }
    }
}
