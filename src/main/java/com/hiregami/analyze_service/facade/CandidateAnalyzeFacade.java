package com.hiregami.analyze_service.facade;

import com.hiregami.analyze_service.dataProcessor.DataProcessor;
import com.hiregami.analyze_service.dto.Workspace;
import com.hiregami.analyze_service.model.Score;
import com.hiregami.analyze_service.model.Skill;
import com.hiregami.data_extraction_library.dto.ProfileContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@AllArgsConstructor
public class CandidateAnalyzeFacade {

    @Autowired
    private DataProcessor aspects;

    public String analyzeCandidateAccordingToWorkspace(Workspace workspace) {
        ProfileContext context = createContext(null, null);
        aspects.processFile(context);
        List<Skill> requiredSkills = workspace.getRequiredSkills();
        List<Workspace.Characteristic> desiredCharacteristics = workspace.getDesiredCharacteristics();
        List<String> languages = workspace.getLanguages();
        List<Workspace.ExperienceGap> requiredExperience = workspace.getRequiredExperience();
        aspects.retrieveSkillsMatchingWorkspace(requiredSkills);
        aspects.retrieveCharacteristicsMatchingWorkspace(desiredCharacteristics);
        aspects.retrieveLanguageMatchingWorkspace(languages);
        aspects.retrieveExperienceMatchingWorkspace(requiredExperience);

    }

    private static Score calculateOverallScore() {
        return
    }

    private static int calculateSkillsScore() {

    }

    private static int calculateCharacteristicsScore() {

    }

    //TODO - #1 parsing cv in plain text
    //TODO - #2 api call for finding user skills.
    //TODO we have to send workspace-required skills. and try to evaluate level
    //TODO - #3 api call for finding desiredCharacteristic, we also sending
    //TODO desired chars for workspace and get list of match
    //TODO - #4 compute score for chars and for skills each with scale of 50
    //TODO - #5 create special formula for withdrawing points by experience and language criteria


    private ProfileContext createContext(String contentType, InputStream inputStream) {
        ProfileContext context = new ProfileContext();
        context.set("contentType", contentType);
        context.set("inputStream", inputStream);
        return context;
    }
}


