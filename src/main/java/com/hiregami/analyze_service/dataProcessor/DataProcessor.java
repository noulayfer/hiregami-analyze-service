package com.hiregami.analyze_service.dataProcessor;

import com.hiregami.data_extraction_library.annotation.AwsComprehendProcess;
import com.hiregami.data_extraction_library.annotation.FileProcessor;
import com.hiregami.data_extraction_library.dto.ProfileContext;
import org.springframework.stereotype.Component;

@Component
public class DataProcessor {

    @FileProcessor
    @SuppressWarnings("unused")
    public void processFile(ProfileContext context) {
        // aspect works
    }

    @AwsComprehendProcess
    @SuppressWarnings("unused")
    public void createCandidateProfile(ProfileContext context) {
        // aspect works
    }
}