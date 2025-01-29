package com.hiregami.analyze_service.model.skill_impl;

import com.hiregami.analyze_service.model.Level;
import com.hiregami.analyze_service.model.Skill;

public class OptionalSkill extends Skill {
    private static final int OPTIONAL_WEIGHT = 1;
    public OptionalSkill(String name, Level level) {
        super(name, OPTIONAL_WEIGHT, level);
    }
}
