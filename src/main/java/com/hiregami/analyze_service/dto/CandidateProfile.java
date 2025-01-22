package com.hiregami.analyze_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CandidateProfile {
    private String name;
    private List<String> skills;
}

