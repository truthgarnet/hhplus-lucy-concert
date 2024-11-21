package com.hhplus.concert.outbox.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.concert.concertReservation.infra.ConcertReservationEntity;
import com.hhplus.concert.outbox.infra.OutboxEntity;
import com.hhplus.concert.outbox.infra.OutboxJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    @Autowired
    private OutboxJpaRepository outboxJpaRepository;

    public void processReservation(ConcertReservationEntity reservation) throws JsonProcessingException {
        OutboxEntity outbox = new OutboxEntity("RESERVATION_CREATED", new ObjectMapper().writeValueAsString(reservation));

        outboxJpaRepository.save(outbox);
    }
}
