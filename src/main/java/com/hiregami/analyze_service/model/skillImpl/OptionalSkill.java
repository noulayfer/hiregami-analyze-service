package com.hiregami.analyze_service.model.skillImpl;

import com.hiregami.analyze_service.model.Skill;

public class OptionalSkill extends Skill {
    private static final int OPTIONAL_WEIGHT = 1;
    public OptionalSkill(String name, Skill.Level level) {
        super(name, OPTIONAL_WEIGHT, level);
    }
}
