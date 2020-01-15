package job.model;

import java.util.*;

public class ExperienceDetail {
	
	protected String UserAccountId;
	protected boolean IsWorking;
	protected Date StartDate;
	protected Date EndDate;
	protected String JobTitle;
	protected boolean FullPartTime;
	protected String CompanyName;
	protected String WorkSiteCity;
	protected String WorkSiteState;
	protected String WorkSiteCountry;
	protected String WorkSitePostCode;
	protected String Description;
	
	public ExperienceDetail() {
		super();
	}

	public ExperienceDetail(String userAccountId, boolean isWorking, Date startDate, Date endDate, String jobTitle,
			boolean fullPartTime, String companyName, String workSiteCity, String workSiteState, String workSiteCountry,
			String workSitePostCode, String description) {
		
		this.UserAccountId = userAccountId;
		this.IsWorking = isWorking;
		this.StartDate = startDate;
		this.EndDate = endDate;
		this.JobTitle = jobTitle;
		this.FullPartTime = fullPartTime;
		this.CompanyName = companyName;
		this.WorkSiteCity = workSiteCity;
		this.WorkSiteState = workSiteState;
		this.WorkSiteCountry = workSiteCountry;
		this.WorkSitePostCode = workSitePostCode;
		this.Description = description;
	}

	public String getUserAccountId() {
		return UserAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.UserAccountId = userAccountId;
	}

	public boolean isIsWorking() {
		return IsWorking;
	}

	public void setIsWorking(boolean isWorking) {
		this.IsWorking = isWorking;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		this.StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		this.EndDate = endDate;
	}

	public String getJobTitle() {
		return JobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.JobTitle = jobTitle;
	}

	public boolean isFullPartTime() {
		return FullPartTime;
	}

	public void setFullPartTime(boolean fullPartTime) {
		this.FullPartTime = fullPartTime;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		this.CompanyName = companyName;
	}

	public String getWorkSiteCity() {
		return WorkSiteCity;
	}

	public void setWorkSiteCity(String workSiteCity) {
		this.WorkSiteCity = workSiteCity;
	}

	public String getWorkSiteState() {
		return WorkSiteState;
	}

	public void setWorkSiteState(String workSiteState) {
		this.WorkSiteState = workSiteState;
	}

	public String getWorkSiteCountry() {
		return WorkSiteCountry;
	}

	public void setWorkSiteCountry(String workSiteCountry) {
		this.WorkSiteCountry = workSiteCountry;
	}

	public String getWorkSitePostCode() {
		return WorkSitePostCode;
	}

	public void setWorkSitePostCode(String workSitePostCode) {
		this.WorkSitePostCode = workSitePostCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}
	
}
