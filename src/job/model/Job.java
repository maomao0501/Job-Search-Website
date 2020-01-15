package job.model;

import java.util.Date;

public class Job {
	protected int jobId;
	protected Company company;
    protected String mainJobTitle;
    protected Date dateAdvertised;
    protected JobLocation jobLocation;
    protected WorkType workType;
    protected String classification;
    protected String jobDescription;
    protected String pageURL;
    protected UserAccount userAccount;
    
	public Job(int jobId, Company company, String mainJobTitle, Date dateAdvertised, JobLocation jobLocation,
			WorkType workType, String classification, String jobDescription, String pageURL, UserAccount userAccount) {
		this.jobId = jobId;
		this.company = company;
		this.mainJobTitle = mainJobTitle;
		this.dateAdvertised = dateAdvertised;
		this.jobLocation = jobLocation;
		this.workType = workType;
		this.classification = classification;
		this.jobDescription = jobDescription;
		this.pageURL = pageURL;
		this.userAccount = userAccount;
	}
	
	public Job(int jobId) {
		this.jobId = jobId;
	}

	public Job(Company company, String mainJobTitle, Date dateAdvertised, JobLocation jobLocation, WorkType workType,
			String classification, String jobDescription, String pageURL, UserAccount userAccount) {
		this.company = company;
		this.mainJobTitle = mainJobTitle;
		this.dateAdvertised = dateAdvertised;
		this.jobLocation = jobLocation;
		this.workType = workType;
		this.classification = classification;
		this.jobDescription = jobDescription;
		this.pageURL = pageURL;
		this.userAccount = userAccount;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMainJobTitle() {
		return mainJobTitle;
	}

	public void setMainJobTitle(String mainJobTitle) {
		this.mainJobTitle = mainJobTitle;
	}

	public Date getDateAdvertised() {
		return dateAdvertised;
	}

	public void setDateAdvertised(Date dateAdvertised) {
		this.dateAdvertised = dateAdvertised;
	}

	public JobLocation getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(JobLocation jobLocation) {
		this.jobLocation = jobLocation;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}