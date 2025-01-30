package com.hiregami.analyze_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Language {
    private String name;
    private Level level;
    private static byte contributionInOverallScore;

    public enum Level {
        NATIVE(1),
        UPPER_INTERMEDIATE(0.7),
        INTERMEDIATE(0.4),
        BEGINNER(0.2),
        NONE(0);

        final double k;

        Level(double k) {
            this.k = k;
        }
    }
}
