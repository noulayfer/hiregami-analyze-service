package com.hiregami.analyze_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Skill {
    private String name;
    private int weight;
    private Level levelOfKnowledge;
    private static byte contributionInOverallScore;

    public enum Level {
        ADVANCED(1),
        INTERN(0.7),
        NOVICE(0.4),
        NONE(0);

        final double k;

        Level(double k) {
            this.k = k;
        }
    }
}
