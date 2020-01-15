package job.model;


public class EducationDetail {
	
	protected String UserAccountId;
	protected String CertificateDegree;
	protected String Major;
	protected String University;
	
	public EducationDetail() {
		super();
	}

	public EducationDetail(String userAccountId, String certificateDegree, String major, String university) {
		this.UserAccountId = userAccountId;
		this.CertificateDegree = certificateDegree;
		this.Major = major;
		this.University = university;
	}

	public String getUserAccountId() {
		return UserAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.UserAccountId = userAccountId;
	}

	public String getCertificateDegree() {
		return CertificateDegree;
	}

	public void setCertificateDegree(String certificateDegree) {
		this.CertificateDegree = certificateDegree;
	}

	public String getMajor() {
		return Major;
	}

	public void setMajor(String major) {
		this.Major = major;
	}

	public String getUniversity() {
		return University;
	}

	public void setUniversity(String university) {
		this.University = university;
	}
}
