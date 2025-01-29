package com.hiregami.analyze_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.hiregami.analyze_service.model.Skill;
import lombok.Data;
import lombok.Value;

@Value
public class CandidateProfile {
  String name;
  List<Skill> skills;

  public CandidateProfile(String name) {
    this.name = name;
    this.skills = new ArrayList<>();
  }
  public void addSkill(Skill skill) {
    skills.add(skill);
  }

  public int calculateScore(Workspace workspace) {
    int score = 0;

    for (Skill skill : workspace.getRequiredSkills()) {
      if (skills.contains(skill.getName())) {
        score += skill.getWeight();
      }
    }

    int maxScore = workspace.getMaxScore();
    return (int) ((score / (double) maxScore) * 100);
  }
}
