package com.hiregami.analyze_service.dto;

import com.hiregami.analyze_service.model.Skill;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class Workspace {
    String title;
    List<Skill> requiredSkills;
    List<ExperienceGap> requiredExperience;
    List<String> languages;
    List<Characteristic> desiredCharacteristics;
    List<CandidateProfile> candidateProfiles;

    @Data
    public static class ExperienceGap {
        private int minYears;
        private int maxYears;
    }

    //TODO skills implementations also may implement Characteristic and we will use common weights
    @Data
    public static class Characteristic {
        private String name;
        private int weight;
        private static byte contributionInOverallScore;
    }

    public void addSkill(Skill skill) {
        requiredSkills.add(skill);
    }

    public void addCandidate(CandidateProfile candidateProfile) {
        candidateProfiles.add(candidateProfile);
    }

    public int getMaxScore() {
        return requiredSkills.stream().mapToInt(Skill::getWeight).sum();
    }
}
