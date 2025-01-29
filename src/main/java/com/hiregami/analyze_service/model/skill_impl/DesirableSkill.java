package com.hiregami.analyze_service.model.skill_impl;

import com.hiregami.analyze_service.model.Level;
import com.hiregami.analyze_service.model.Skill;

public class DesirableSkill extends Skill {
    private static final int DESIRABLE_SKILL = 3;
    public DesirableSkill(String name, Level level) {
        super(name, DESIRABLE_SKILL, level);
    }
}
