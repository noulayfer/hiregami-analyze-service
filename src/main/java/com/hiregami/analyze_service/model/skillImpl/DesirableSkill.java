package com.hiregami.analyze_service.model.skillImpl;

import com.hiregami.analyze_service.model.Skill;

public class DesirableSkill extends Skill {
    private static final int DESIRABLE_SKILL = 3;
    public DesirableSkill(String name, Skill.Level level) {
        super(name, DESIRABLE_SKILL, level);
    }
}
