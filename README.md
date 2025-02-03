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

## 5. Calculate CandidateScore

#### Skill score
#### Characteristics score
#### Language score
#### Experience score

We should retrieve from Workspace object - 
**weight** for each of these fields.

There is have to be common 
**class Score - byte: maxScore, byte: exactScore**

By default, all skills and characteristics evaluated as Advanced.
If we could receive other information from CV we will set
matching fields.

### Step 5.1 Calculate skill score

### **Skill Requirements**

In each workspace, required skills are defined with the following attributes:
- **Name:** The specific skill (e.g., "Java").
- **Weight:** The importance of the skill, categorized as:
  - **1** - Optional
  - **3** - Desirable
  - **5** - Essential
- **Level of Knowledge:** The proficiency required for the skill:
  - **None (0)**
  - **Novice (0.4)**
  - **Intern (0.7)**
  - **Advanced(1.0)**

### **Candidate Profile**

A candidate's profile contains a list of skills with their corresponding proficiency levels. By default, skills are evaluated as "Advanced" unless specified otherwise based on CV data.

### **Scoring Process**

The candidate's skills are compared to the workspace requirements to calculate a score.

1. **If the candidate's skill level is greater than or equal to the required level:**
  - Full credit is awarded for that skill.
2. **If the candidate's skill level is below the required level:**
  - Partial credit is awarded based on the candidate's actual skill level.

---

### 5.2 Characteristics Evaluation

### **Characteristic Requirements**

In each workspace, required characteristics are defined with the following attributes:
- **Name:** The specific characteristic (e.g., "Leadership").
- **Weight:** The importance of the characteristic, categorized as:
  - **1** - Optional
  - **3** - Desirable
  - **5** - Essential
- **Level of Knowledge:** The proficiency required for the characteristic:
  - **None (0)**
  - **Novice (0.4)**
  - **Intern (0.7)**
  - **Advanced (1.0)**

### **Candidate Profile**

A candidate's profile contains a list of characteristics with their corresponding proficiency levels. By default, characteristics are evaluated as "Advanced" unless specified otherwise based on CV data.

### **Scoring Process**

The candidate's characteristics are compared to the workspace requirements to calculate a score.

1. **If the candidate's characteristic level is greater than or equal to the required level:**
  - Full credit is awarded for that characteristic.
2. **If the candidate's characteristic level is below the required level:**
  - Partial credit is awarded based on the candidate's actual characteristic level.

---

###### Example Scenario

###### **Workspace Requirements:**
- **Java:** Essential, Intermediate Level
- **Spring:** Desirable, Beginner Level
- **Docker:** Optional, Beginner Level
- **Leadership:** Essential, Advanced Level
- **Communication:** Desirable, Intern Level

##### **Candidate Profile:**
- **Java:** Upper Intermediate
- **Spring:** Beginner
- **Leadership:** Advanced
- **Communication:** Novice

##### **Contribution:**
- Skills contribute **40%** to the total score.
- Characteristics contribute **30%** to the total score.

##### **Calculation:**
1. For **Java** and **Spring**, the candidate meets or exceeds the requirements, earning full points.
2. For **Leadership**, the candidate matches the requirement, earning full points.
3. For **Communication**, the candidate is below the required level, earning partial points.

The scores are normalized to fit within the respective contributions.

##### **Normalization**

The score is normalized to fit the overall contribution percentage assigned to characteristics in the workspace (e.g., 30%).

**Formula:**

**Normalized Score = (Candidate Score / Workspace Max Score) × Contribution in Overall Score**

Where:
- **Candidate Score:** The sum of scores for all characteristics based on the candidate's proficiency.
- **Workspace Max Score:** The maximum possible score a candidate can achieve if they meet all characteristic requirements perfectly.
- **Contribution in Overall Score:** The percentage weight assigned to characteristics in the overall evaluation (e.g., 30%).


---



### 5.3 **Experience Evaluation System**

We'll integrate the experience evaluation into the existing framework, ensuring it aligns with the same principles of **weighted scoring** and **normalization**. Here's how we'll approach it:

---

###### Experience Evaluation Overview

###### **Key Components:**
1. **Area of Experience** (e.g., **FinTech**, **Banking**)
2. **Role** (e.g., **Software Developer**)
3. **Education** (e.g., **Bachelor in CS**)
4. **Commercial Experience** (Minimum required years)
5. **Overall Experience in Sphere** (Broader experience related to the field)

---

###### **Data Model**

#### **ExperienceRequirement Class**

```java
@Data
public class ExperienceRequirement {
    private List<String> areas;                   // e.g., ["FinTech", "Banking"]
    private String role;                         // Required role, e.g., "Software Developer"
    private EducationRequirement education;     // Nested class for education details
    private int minCommercialExperience;         // Minimum required years of commercial experience
    private int overallExperienceInSphere;       // Preferred overall years of experience
    private int areaImportanceWeight;            // Weight to define the importance of specific areas
    private static byte contributionInOverallScore; // Weight contribution to the overall score
}
```

### **EducationRequirement Class**

```java
@Data
public class EducationRequirement {
    private String degree;       // e.g., "Bachelor in CS"
    private int weight;          // Defines the importance of the education in scoring
}
```

---

### ** Candidate Experience Profile**

```java
@Data
public class CandidateExperience {
    private List<String> areas;           // Areas where the candidate has experience
    private String role;                  // Candidate's role
    private String education;             // Candidate's education
    private int commercialExperience;     // Candidate's commercial experience in years
    private int overallExperience;        // Candidate's overall experience in years
}
```

---

### **Evaluation Logic**

### **Scoring Process**

1. **Area of Experience:**
  - If the candidate has experience in the required area:
    - Full credit based on `areaImportanceWeight`.
  - If not:
    - Partial credit or deduction based on importance.

2. **Role:**
  - Exact match: Full points.
  - Related role: Partial points.

3. **Education:**
  - Matches the required degree: Full points.
  - Related field: Partial points, adjusted by the `weight` parameter.

4. **Commercial Experience:**
  - If `candidate.commercialExperience >= minCommercialExperience`, full credit.
  - Otherwise, partial points based on the ratio.

5. **Overall Experience in Sphere:**
  - Similar logic to commercial experience, comparing years against the `overallExperienceInSphere`.

---

### **Normalization Formula**

**Normalized Score = (Candidate Score / Max Possible Score) × Contribution in Overall Score**

Where:
- **Candidate Score:** Total score based on all matching experience criteria.
- **Max Possible Score:** The sum of maximum scores for all experience-related requirements.
- **Contribution in Overall Score:** The weight assigned to experience in the overall candidate evaluation.

---

### 5.4 Workspace Language Requirements

## Overview

The **Workspace Language Requirements** define the necessary language skills for a job position. This includes mandatory languages that are critical for the role and preferred languages that provide additional value.

---

#### LanguageRequirement Class

```java
@Data
@AllArgsConstructor
class LanguageRequirement {
    private List<String> languageGroup;       // Group of languages (e.g., ["Ukrainian", "Russian"])
    private Language.Level requiredLevel;     // Minimum required level of proficiency
    private boolean mandatory;                // True if the language is mandatory
    private int priority;                     // 5 = Main (critical), 3 = Preferred
}
```

#### **Language Proficiency Levels:**

```java
public enum Level {
    NONE(0),
    BEGINNER(1.0),
    INTERMEDIATE(1.2),
    UPPER_INTERMEDIATE(1.3),
    NATIVE(1.4);

    final double multiplier;

    Level(double multiplier) {
        this.multiplier = multiplier;
    }
}
```

---

#### Example: Two Main Languages (Both Required)

#### **Workspace Language Requirements**

```java
List<LanguageRequirement> languageRequirements = List.of(
    new LanguageRequirement(List.of("Ukrainian"), Language.Level.INTERMEDIATE, true, 5), // Mandatory
    new LanguageRequirement(List.of("Russian"), Language.Level.INTERMEDIATE, true, 5),   // Mandatory
    new LanguageRequirement(List.of("English"), Language.Level.UPPER_INTERMEDIATE, false, 3) // Preferred
);
```

---

#### Candidate Examples

#### **Candidate 1: Meets All Requirements**

```java
Candidate candidate = new Candidate("John Doe");
candidate.addLanguage("Ukrainian", Language.Level.NATIVE);
candidate.addLanguage("Russian", Language.Level.UPPER_INTERMEDIATE);
candidate.addLanguage("English", Language.Level.INTERMEDIATE);
```

- **Ukrainian:** NATIVE (1.4 multiplier) ✅
- **Russian:** UPPER_INTERMEDIATE (1.3 multiplier) ✅
- **English:** INTERMEDIATE (1.2 multiplier) ⚠️ *(Below preferred level)*

#### **Score Calculation:**

- **Candidate Score:** `(5 × 1.4) + (5 × 1.3) + (3 × 1.2) = 7 + 6.5 + 3.6 = 17.1`
- **Max Possible Score:** `5 + 5 + 3 = 13`
- **Normalized Score (for 15% weight):** `(17.1 / 13) × 15 = 19.73` → **Capped at 15**

---

#### **Candidate 2: Missing One Mandatory Language**

```java
Candidate candidate = new Candidate("Jane Smith");
candidate.addLanguage("Ukrainian", Language.Level.NONE);  // ❌ Missing
candidate.addLanguage("Russian", Language.Level.NATIVE);   // ✅
candidate.addLanguage("English", Language.Level.NATIVE);   // ✅
```

- **Ukrainian:** NONE (0 multiplier) ❌ *(Mandatory language missing)*
- **Russian:** NATIVE (1.4 multiplier) ✅
- **English:** NATIVE (1.4 multiplier) ✅

### **Penalty Applied:**
- Since **Ukrainian** is mandatory and missing, the candidate receives a **severe penalty**, reducing the score to **zero or near-zero**.

---

#### Workspace Representation (JSON Example)

```json
{
  "languageRequirements": [
    { "languageGroup": ["Ukrainian"], "requiredLevel": "INTERMEDIATE", "mandatory": true, "priority": 5 },
    { "languageGroup": ["Russian"], "requiredLevel": "INTERMEDIATE", "mandatory": true, "priority": 5 },
    { "languageGroup": ["English"], "requiredLevel": "UPPER_INTERMEDIATE", "mandatory": false, "priority": 3 }
  ]
}
```

---

#### Key Rules

1. **Mandatory Languages:**
  - Missing any mandatory language leads to a **severe penalty**.
2. **Preferred Languages:**
  - Contribute positively to the score but are **not deal-breakers**.
3. **Proficiency Levels:**
  - Higher levels (e.g., **Native**) receive **bonus multipliers**.
4. **Normalization:**
  - The final score is **normalized** based on the workspace's language contribution weight.


Here's how we can structure the response for the **Analyze Candidate** API, incorporating the updated `CandidateProfile` along with the four evaluation scores.

---

### **API Response Structure**

The response will include:

1. **Updated CandidateProfile**
2. **Evaluation Scores:**
  - **Experience Score**
  - **Skills Score**
  - **Characteristics Score**
  - **Languages Score**

### **Example Response**

```json
{
  "candidateProfile": {
    "candidateProfileId": "123e4567-e89b-12d3-a456-426614174000",
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
      { "name": "English", "level": "NATIVE" },
      { "name": "Ukrainian", "level": "INTERMEDIATE" }
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
  "scores": {
    "experienceScore": 12,
    "skillsScore": 38,
    "characteristicsScore": 25,
    "languagesScore": 15
  }
}
```

---

### **Key Points:**

- **CandidateProfile** is updated if new information is retrieved during the analysis.
- **Scores** are normalized based on workspace-defined contributions (e.g., skills might contribute up to 40 points, languages up to 15, etc.).
- **Database Updates:**
  - The **CandidateProfile** is updated if necessary.
  - The **Workspace** is updated in the database if changes are identified during the analysis.

Would you like me to update the API documentation with this response structure?