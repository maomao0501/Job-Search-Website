package job.model;

import java.util.Date;

public class JobApplyActivity {
	protected int jobApplyActivityId;
    protected Job job;
    protected UserAccount userAccount;
    protected Date applyDate;
    
	public JobApplyActivity(Job job, UserAccount userAccount, Date applyDate) {
		this.job = job;
		this.userAccount = userAccount;
		this.applyDate = applyDate;
	}

	
	public JobApplyActivity(int jobApplyActivityId) {
		this.jobApplyActivityId = jobApplyActivityId;
	}


	public JobApplyActivity(int jobApplyActivityId, Job job, UserAccount userAccount, Date applyDate) {
		this.jobApplyActivityId = jobApplyActivityId;
		this.job = job;
		this.userAccount = userAccount;
		this.applyDate = applyDate;
	}


	public int getJobApplyActivityId() {
		return jobApplyActivityId;
	}


	public void setJobApplyActivityId(int jobApplyActivityId) {
		this.jobApplyActivityId = jobApplyActivityId;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	public UserAccount getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	public Date getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
}
