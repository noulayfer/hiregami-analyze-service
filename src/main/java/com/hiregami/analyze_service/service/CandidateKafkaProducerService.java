package com.hiregami.analyze_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiregami.analyze_service.dto.CandidateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateKafkaProducerService {

    private static final String TOPIC = "candidate-profiles-topic";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendCandidateProfile(CandidateProfile candidateProfile, String metadata) {
        try {
            String payload = objectMapper.writeValueAsString(candidateProfile);
            kafkaTemplate.send(TOPIC, metadata, payload);
            System.out.println("Message sent to Kafka: " + payload);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing CandidateProfile: " + e.getMessage());
        }
    }
}
