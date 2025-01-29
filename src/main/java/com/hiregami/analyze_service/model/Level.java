package com.hiregami.analyze_service.model;

public enum Level {
    ADVANCED(1),
    INTERN(0.7),
    NOVICE(0.4),
    NONE(0);

    final double k;

    private Level(double k) {
        this.k = k;
    }
}
