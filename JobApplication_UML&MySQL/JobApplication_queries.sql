USE JobApplication;

# Q1: List top 10 job posters with the biggest job posting numbers, including their UserAccountId, Name and contact information.
SELECT UserAccount.UserAccountId,  UserAccount.FirstName, 
UserAccount.LastName, UserAccount.Email, UserAccount.ContactNumber,
COUNT(*) AS CNT_JOB
FROM Job INNER JOIN UserAccount
ON UserAccount.UserAccountId = Job.UserAccountId
GROUP BY UserAccount.UserAccountId
ORDER BY CNT DESC
LIMIT 10;

# Q2: What is the ratio of the number of job seekers who applied any job to the total number of registered job seekers.
SELECT (
SELECT COUNT(*) AS CNT_SEEKER_APPLIED
FROM (
SELECT DISTINCT UserAccountId
FROM JobApplyActivity) 
AS SEEKER_APPLIED) / (
SELECT COUNT(*) AS CNT_SEEKER_TOTAL
FROM UserAccount
WHERE UserTypeId = 'S') 
AS RATIO_SEEKER_ACTIVE;

#	Q3: List the companies have the most job postings. Including company name and URL
SELECT Company.CompanyName, Company.CompanyId, 
Company.URL, Company.State, COUNT(*) AS CNT_JOB
FROM Job INNER JOIN Company
ON Job.CompanyId = Company.CompanyId
GROUP BY Company.CompanyId
HAVING Company.State = 'CA'
ORDER BY CNT_JOB DESC;

