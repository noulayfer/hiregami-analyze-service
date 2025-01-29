package com.hiregami.analyze_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public abstract class Skill {
    private String name;
    private int weight;
    private Level levelOfKnowledge;
}
