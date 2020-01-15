package job.model;

public class SeekerProfile {
	
	protected String UserAccountId;
	protected String Industry;
	protected String CurrentSalary;
	protected String SalaryUnit;
	protected String Resume;
	
	public SeekerProfile(String userAccountId, String industry, String currentSalary, String salaryUnit,
			String resume) {
		this.UserAccountId = userAccountId;
		this.Industry = industry;
		this.CurrentSalary = currentSalary;
		this.SalaryUnit = salaryUnit;
		this.Resume = resume;
	}

	public SeekerProfile() {
		super();
	}

	public String getUserAccountId() {
		return UserAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.UserAccountId = userAccountId;
	}

	public String getIndustry() {
		return Industry;
	}

	public void setIndustry(String industry) {
		this.Industry = industry;
	}

	public String getCurrentSalary() {
		return CurrentSalary;
	}

	public void setCurrentSalary(String currentSalary) {
		this.CurrentSalary = currentSalary;
	}

	public String getSalaryUnit() {
		return SalaryUnit;
	}

	public void setSalaryUnit(String salaryUnit) {
		this.SalaryUnit = salaryUnit;
	}

	public String getResume() {
		return Resume;
	}

	public void setResume(String resume) {
		this.Resume = resume;
	}	
}
