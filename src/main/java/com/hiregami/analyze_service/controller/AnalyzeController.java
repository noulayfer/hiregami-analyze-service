package com.hiregami.analyze_service.controller;

import com.hiregami.analyze_service.dto.CandidateProfile;
import com.hiregami.analyze_service.dto.Workspace;
import com.hiregami.analyze_service.service.CandidateKafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
  @Autowired private CandidateKafkaProducerService kafkaProducerService;

  @PostMapping("/candidate")
  public ResponseEntity<String> processCandidate(
          @RequestBody CandidateProfile candidateProfile, @RequestBody(required = false) Workspace workspace, @RequestBody(required = false) String metadata) {
    kafkaProducerService.sendCandidateProfile(candidateProfile, workspace, metadata);
    return ResponseEntity.ok("CandidateProfile sent to Kafka successfully.");
  }
}
