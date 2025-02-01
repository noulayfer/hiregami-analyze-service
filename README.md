# Hiregami Analyze Service

## Available API Operations

- [Analyze Candidate](#analyze-candidate)

---

## Analyze Candidate

**Endpoint:** `/analyze/candidate/{candidateProfileId}`

### Purpose

The primary purpose of this endpoint is to retrieve available fields from a `CandidateProfile`.

## 1. Receiving api request

### Request Body

The request body must be in JSON format and includes the following parameters:

#### 1. **candidateProfileId** (Required)

- `candidateProfileId` (UUID): A unique identifier for the candidate profile.

#### 2. **CandidateProfile** (Required)

Represents the candidate's profile with the following fields:

- `firstName` (String): Candidate's first name.
- `lastName` (String): Candidate's last name.
- `skills` (List<SkillInfo>): A list of skills the candidate possesses.
- `characteristics` (List<CharacteristicInfo>): A list of the candidate's characteristics.
- `languages` (List<LanguageInfo>): Languages known by the candidate.
- `experience` (List<ExperienceInfo>): Work experiences of the candidate.

**SkillInfo:**
```java
class SkillInfo {
    private String name;
    private Level levelOfKnowledge;

    public enum Level {
        ADVANCED(1),
        INTERN(0.7),
        NOVICE(0.4),
        NONE(0);
    }
}
```

**CharacteristicInfo:**
```java
public static class CharacteristicInfo {
    private String name;
}
```

**LanguageInfo:**
```java
@AllArgsConstructor
public class LanguageInfo {
    private String name;
    private Level level;

    public enum Level {
        NATIVE(1),
        UPPER_INTERMEDIATE(0.7),
        INTERMEDIATE(0.4),
        BEGINNER(0.2),
        NONE(0);
    }
}
```

**ExperienceInfo:**
```java
public class ExperienceInfo {
    private String company;
    private String area; // e.g., finTech, banking, blockchain
    private String role;
    private int actualCommercialExperience; // Total years of commercial experience
    private int actualOverallExperience;   // Total years of overall experience
    private String education;              // Candidate's education background
}
```

#### 3. **creatingFlow** (Required)

Defines the flow of creation. It can have one of the following values:

- `manual`
- `automated`

#### 4. **Workspace** (Optional)

Provides additional context related to the workspace, with the following fields:

- `title` (String): The title of the workspace.
- `requiredSkills` (List<SkillRequirement>): A list of skills required for the workspace.
- `requiredCharacteristics` (List<CharacteristicRequirement>): Required characteristics for candidates.
- `requiredLanguages` (List<LanguageRequirement>): Languages required for the workspace.
- `desiredExperience` (List<DesiredExperience>): Desired work experiences:
  - `area` (List<String>): Areas like ["finTech", "banking", "blockchain"].
  - `role` (String): Desired role, e.g., "Software Developer".
  - `education` (String): Required education level, e.g., "Bachelor in CS".
  - `minCommercialExperience` (int): Minimum years of commercial experience.
  - `overallExperienceInSphere` (int): Overall years of experience in the relevant sphere.
- `candidateProfiles` (List<CandidateProfile>): Profiles of other candidates in the workspace.

**SkillRequirement:**
```java
class SkillRequirement {
    private String name;
    private int weight; // 1 (optional), 3 (desirable), 5 (essential)
    private Level levelOfKnowledge;
}
```

**CharacteristicRequirement:**
```java
public static class CharacteristicRequirement {
    private String name;
    private int weight; // 1 (optional), 3 (desirable), 5 (essential)
}
```

**LanguageRequirement:**
```java
@AllArgsConstructor
public class LanguageRequirement {
    private String name;
    private Level level;
}
```

### Example Request

```json
{
  "candidateProfileId": "123e4567-e89b-12d3-a456-426614174000",
  "candidateProfile": {
    "firstName": "John",
    "lastName": "Doe",
    "skills": [
      { "name": "Java", "levelOfKnowledge": "ADVANCED" },
      { "name": "AWS", "levelOfKnowledge": "INTERN" }
    ],
    "characteristics": [
      { "name": "Leadership" },
      { "name": "Team Player" }
    ],
    "languages": [
      { "name": "English", "level": "NATIVE" }
    ],
    "experience": [
      {
        "company": "TechCorp",
        "area": "finTech",
        "role": "Developer",
        "actualCommercialExperience": 3,
        "actualOverallExperience": 5,
        "education": "Bachelor in CS"
      }
    ]
  },
  "creatingFlow": "manual",
  "workspace": {
    "title": "Backend Developer Position",
    "requiredSkills": [
      { "name": "Java", "weight": 5, "levelOfKnowledge": "ADVANCED" },
      { "name": "Spring Boot", "weight": 3, "levelOfKnowledge": "NOVICE" }
    ],
    "requiredCharacteristics": [
      { "name": "Problem Solver", "weight": 5 }
    ],
    "requiredLanguages": [
      { "name": "English", "level": "UPPER_INTERMEDIATE" }
    ],
    "desiredExperience": [
      { "area": ["finTech", "banking"], "role": "Software Developer", "education": "Bachelor in CS", "minCommercialExperience": 1, "overallExperienceInSphere": 3 }
    ],
    "candidateProfiles": []
  }
}
```

### Response

The response will include the available fields from the provided `CandidateProfile`, along with any relevant information based on the `Workspace` and `creatingFlow` parameters.

---

For further updates and additional API operations, please refer back to this documentation.

## 2. Choose the flow 
### Step 2.1: Check `creatingFlow` 
- **If `creatingFlow` is `manual`:** Skip to step **X**. 
- **If `creatingFlow` is `automated`:** Proceed to Step 2. 
### Step 2.2: (Reserved for Future Steps - **X**) 

## 3. Retrieve candidate information for automated flow

The service makes an API call to retrieve candidate information from another service called **Candidate Service**. 

**API Call:** ``` GET /candidate/{candidateId}/information ```
- **Request Parameters**: - `candidateId` (UUID): The unique identifier of the candidate. 
- **Response**: - Returns an `InputStream` containing the candidate's information. The API call is performed using a **Feign Client** to handle the communication between microservices. --- For further updates and additional API operations, please refer back to this documentation.

## 4. Form CandidateProfile from candidate information

Make 4 api call in openAI **o3** to retrieve:
#### Skills (4.1)
#### Characteristics (4.2)
#### Language (4.3)
#### Experience (4.4)

### Step 4.1 //TODO
### Step 4.2 //TODO
### Step 4.3 //TODO
### Step 4.4 //TODO