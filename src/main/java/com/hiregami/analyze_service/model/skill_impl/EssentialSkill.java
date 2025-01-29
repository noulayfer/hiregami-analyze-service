package com.hiregami.analyze_service.model.skill_impl;

import com.hiregami.analyze_service.model.Level;
import com.hiregami.analyze_service.model.Skill;

public class EssentialSkill extends Skill {
    private static final int ESSENTIAL_WEIGHT = 5;
    public EssentialSkill(String name, Level level) {
        super(name, ESSENTIAL_WEIGHT, level);
    }
}
