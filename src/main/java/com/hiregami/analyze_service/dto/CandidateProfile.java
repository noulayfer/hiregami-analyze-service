package com.hiregami.analyze_service.dto;

import java.util.List;
import lombok.Data;

@Data
public class CandidateProfile {
  private String name;
  private List<String> skills;
}
