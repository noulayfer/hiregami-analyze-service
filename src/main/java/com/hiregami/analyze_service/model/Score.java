package com.hiregami.analyze_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private byte skillScore;
    private byte characteristicScore;
    private byte experienceScore;
    private byte languageScore;
}
