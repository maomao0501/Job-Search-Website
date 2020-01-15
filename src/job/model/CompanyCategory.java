package job.model;

public class CompanyCategory {
	protected int companyCategoryId;
	protected String companyCategoryDescription;
	
	public CompanyCategory(int companyCategoryId, String companyCategoryDescription) {
		this.companyCategoryId = companyCategoryId;
		this.companyCategoryDescription = companyCategoryDescription;
	}

	public CompanyCategory() {
		super();
	}

	public CompanyCategory(int companyCategoryId) {
		super();
		this.companyCategoryId = companyCategoryId;
	}

	public int getCompanyCategoryId() {
		return companyCategoryId;
	}

	public void setCompanyCategoryId(int companyCategoryId) {
		this.companyCategoryId = companyCategoryId;
	}

	public String getCompanyCategoryDescription() {
		return companyCategoryDescription;
	}

	public void setCompanyCategoryDescription(String companyCategoryDescription) {
		this.companyCategoryDescription = companyCategoryDescription;
	}
	
}

