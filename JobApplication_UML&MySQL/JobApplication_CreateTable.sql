CREATE SCHEMA IF NOT EXISTS JobApplication;
USE JobApplication;

DROP TABLE IF EXISTS JobPostSkillSet;
DROP TABLE IF EXISTS SeekerSkillSet;
DROP TABLE IF EXISTS SkillSet;
DROP TABLE IF EXISTS JobApplyActivity;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS WorkType;
DROP TABLE IF EXISTS JobLocation;
DROP TABLE IF EXISTS ExperienceDetail;
DROP TABLE IF EXISTS EducationDetail;
DROP TABLE IF EXISTS SeekerProfile;
DROP TABLE IF EXISTS UserLog;
DROP TABLE IF EXISTS UserAccount;
DROP TABLE IF EXISTS UserType;
DROP TABLE IF EXISTS CompanyImage;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS CompanyCategory;

CREATE TABLE CompanyCategory (
    CompanyCategoryId INT,
    CompanyCategoryDescription VARCHAR(255),
    CONSTRAINT pk_CompanyCategory_CompanyCategoryId 
    PRIMARY KEY (CompanyCategoryId)
);

CREATE TABLE Company (
    CompanyId INT AUTO_INCREMENT,
    CompanyName VARCHAR(255) NOT NULL,
    URL VARCHAR(255),
    YearFounded VARCHAR(255),
    City VARCHAR(255),
    State VARCHAR(255),
    Country VARCHAR(255),
    ZipCode VARCHAR(255),
    CompanyType VARCHAR(255),
    Description LONGTEXT,
    CompanyCategoryId INT NOT NULL,
    CONSTRAINT pk_Company_CompanyId PRIMARY KEY (CompanyId),
    CONSTRAINT fk_Company_CompanyCategoryId FOREIGN KEY (CompanyCategoryId)
    REFERENCES CompanyCategory (CompanyCategoryId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CompanyImage (
    ImageId INT AUTO_INCREMENT,
    CompanyId INT NOT NULL,
    CompanyImage LONGBLOB,
    CONSTRAINT pk_CompanyImage_ImageId PRIMARY KEY (ImageId),
    CONSTRAINT fk_CompanyImage_CompanyId FOREIGN KEY (CompanyId)
    REFERENCES Company (CompanyId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE UserType (
    UserTypeId CHAR(1) NOT NULL,
    UserTypeName VARCHAR(255) NOT NULL,
    CONSTRAINT pk_UserType_UserTypeId  PRIMARY KEY (UserTypeId)
);

CREATE TABLE UserAccount (
    UserAccountId VARCHAR(255) NOT NULL,
    UserTypeId CHAR(1) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Password VARCHAR(255) NOT NULL,
    DateOfBirth DATE,
    Gender CHAR(1),
    ContactNumber VARCHAR(255),
    RegistrationDate DATE NOT NULL,
    CONSTRAINT pk_UserAccount_UserAccountId PRIMARY KEY (UserAccountId),
    CONSTRAINT fk_UserAccount_UserTypeId FOREIGN KEY (UserTypeId)
    REFERENCES  UserType(UserTypeId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE UserLog (
    UserAccountId VARCHAR(255) NOT NULL,
    LastLoginDate DATE NOT NULL,
    LastJobApplyDate DATE,
    LastJobPostDate DATE,
    CONSTRAINT pk_UserLog_UserAccountId PRIMARY KEY (UserAccountId),
    CONSTRAINT fk_UserAccount_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SeekerProfile (
    UserAccountId VARCHAR(255) NOT NULL,
    Industry VARCHAR(255) NOT NULL,
    CurrentSalary VARCHAR(255),
    SalaryUnit VARCHAR(255),
    Resume LONGTEXT,
    CONSTRAINT pk_SeekerProfile_UserAccountId PRIMARY KEY (UserAccountId),
    CONSTRAINT fk_SeekerProfile_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE EducationDetail (
    UserAccountId VARCHAR(255) NOT NULL,
    CertificateDegree VARCHAR(255) NOT NULL,
    Major VARCHAR(255),
    University VARCHAR(255),
    CONSTRAINT pk_EducationDetail_UserAccountId PRIMARY KEY (UserAccountId),
    CONSTRAINT fk_EducationDetail_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES SeekerProfile (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ExperienceDetail (
    UserAccountId VARCHAR(255) NOT NULL,
    IsWorking CHAR(1) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    JobTitle VARCHAR(255) NOT NULL,
    FullTime CHAR(1),
    CompanyName VARCHAR(255) NOT NULL,
    WorkSiteCity VARCHAR(255),
    WorkSiteCounty VARCHAR(255),
    WorkSiteState VARCHAR(255),
    WorkSitePostCode VARCHAR(255),
    Description VARCHAR(255),
    CONSTRAINT pk_ExperienceDetail_UserAccountId PRIMARY KEY (UserAccountId),
    CONSTRAINT fk_ExperienceDetail_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES SeekerProfile (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE  
);

CREATE TABLE WorkType (
    WorkType INT NOT NULL,
    Detail VARCHAR(255) NOT NULL,
    CONSTRAINT pk_Work_TypeWorkType PRIMARY KEY (WorkType)
);

CREATE TABLE JobLocation (
    JobLocationId INT AUTO_INCREMENT,
    StreetAddress VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    State VARCHAR(255) NOT NULL,
    Country VARCHAR(255) NOT NULL,
    Zip VARCHAR(255) NOT NULL,
    CONSTRAINT pk_JobLocation_JobLocationId PRIMARY KEY (JobLocationId)
);

CREATE TABLE Job (
    JobId INT AUTO_INCREMENT,
    CompanyId INT,
    MainJobTitle VARCHAR(255) NOT NULL,
    DateAdvertised DATE NOT NULL,
    JobLocationId INT,
    WorkType INT,
    Classification VARCHAR(255) NOT NULL,
    JobDescription LONGTEXT NOT NULL,
    PageURL TEXT,
    UserAccountId VARCHAR(255) NOT NULL,
    CONSTRAINT pk_Job_JobId PRIMARY KEY (JobId),
    CONSTRAINT fk_Job_JobLocationId FOREIGN KEY (JobLocationId)
    REFERENCES JobLocation (JobLocationId) 
    ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_Job_WorkType FOREIGN KEY (WorkType)
    REFERENCES WorkType (WorkType)
    ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_Job_CompanyId FOREIGN KEY (CompanyId)
    REFERENCES Company (CompanyId)
    ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_Job_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE JobApplyActivity (
    JobApplyActivityId INT AUTO_INCREMENT,
    JobId INT NOT NULL,
    UserAccountId VARCHAR(255) NOT NULL,
    ApplyDate DATE NOT NULL,
    CONSTRAINT pk_JobApplyActivity_JobApplyActivityId 
    PRIMARY KEY (JobApplyActivityId),
    CONSTRAINT uq_JobApplyActivity_JobApply
    UNIQUE (JobId,UserAccountId), 
    CONSTRAINT fk_JobApplyActivity_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_JobApplyActivity_JobId FOREIGN KEY (JobId)
    REFERENCES Job (JobId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SkillSet (
    SkillSetId INT AUTO_INCREMENT,
    SkillSetName VARCHAR(255) NOT NULL,
    CONSTRAINT pk_SkillSet_SkillSetId PRIMARY KEY (SkillSetId)
);

CREATE TABLE SeekerSkillSet (
    SeekerSkillSetId INT AUTO_INCREMENT,
    UserAccountId VARCHAR(255) NOT NULL,
    SkillSetId INT NOT NULL,
    SkillLevel VARCHAR(255),
    CONSTRAINT pk_SeekerSkillSet_SeekerSkillSetId 
    PRIMARY KEY (SeekerSkillSetId),
    CONSTRAINT uq_SeekerSkillSet_SeekerSkill
    UNIQUE (UserAccountId,SkillSetId),
    CONSTRAINT fk_SeekerSkillSet_UserAccountId FOREIGN KEY (UserAccountId)
    REFERENCES SeekerProfile (UserAccountId)
    ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_SeekerSkillSet_SkillSetId FOREIGN KEY (SkillSetId)
    REFERENCES SkillSet (SkillSetId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE JobPostSkillSet (
    JobPostSkillSetId INT AUTO_INCREMENT,
    JobId INT NOT NULL,
    SkillSetId INT NOT NULL,
    SkillLevel VARCHAR(255),
    CONSTRAINT pk_JobPostSkillSet_JobPostSkillSetId PRIMARY KEY (JobPostSkillSetId),
    CONSTRAINT uq_JobPostSkillSet_JobPostSkill
    UNIQUE (JobId,SkillSetId),
    CONSTRAINT fk_JobPostSkillSet_JobId FOREIGN KEY (JobId)
    REFERENCES Job (JobId)
    ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_JobPostSkillSet_SkillSetId FOREIGN KEY (SkillSetId)
    REFERENCES SkillSet  (SkillSetId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


