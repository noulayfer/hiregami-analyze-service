package com.hiregami.analyze_service.controller;

import com.hiregami.analyze_service.dto.CandidateProfile;
import com.hiregami.analyze_service.service.CandidateKafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
    private final CandidateKafkaProducerService kafkaProducerService;

    @PostMapping("/candidate")
    public ResponseEntity<String> processCandidate(
            @RequestBody CandidateProfile candidateProfile,
            @RequestBody String metadata
    ) {
        kafkaProducerService.sendCandidateProfile(candidateProfile, metadata);
        return ResponseEntity.ok("CandidateProfile sent to Kafka successfully.");
    }
}
