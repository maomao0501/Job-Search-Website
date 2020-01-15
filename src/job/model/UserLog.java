package job.model;

import java.util.Date;

public class UserLog {
	protected UserAccount userAccount;
	protected Date lastLoginDate;
	protected Date lastJobApplyDate;
	protected Date lastJobPostDate;

	public UserLog(UserAccount userAccount, Date lastLoginDate, Date lastJobApplyDate, Date lastJobPostDate) {
		super();
		this.userAccount = userAccount;
		this.lastLoginDate = lastLoginDate;
		this.lastJobApplyDate = lastJobApplyDate;
		this.lastJobPostDate = lastJobPostDate;
	}

	public UserLog(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastJobApplyDate() {
		return lastJobApplyDate;
	}

	public void setLastJobApplyDate(Date lastJobApplyDate) {
		this.lastJobApplyDate = lastJobApplyDate;
	}

	public Date getLastJobPostDate() {
		return lastJobPostDate;
	}

	public void setLastJobPostDate(Date lastJobPostDate) {
		this.lastJobPostDate = lastJobPostDate;
	}

}