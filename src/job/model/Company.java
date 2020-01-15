package job.model;

public class Company {
	protected int companyId;
	protected String companyName;
	protected String URL;
	protected String yearFounded;
	protected String city;
	protected String state;
	protected String country;
	protected String zipCode;
	protected String companyType;
	protected String description;
	protected CompanyCategory companyCategory;
	
	public Company(int companyId, String companyName, String uRL, String yearFounded, String city, String state,
			String country, String zipCode, String companyType, String description, CompanyCategory companyCategory) {
		this.companyId = companyId;
		this.companyName = companyName;
		URL = uRL;
		this.yearFounded = yearFounded;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.companyType = companyType;
		this.description = description;
		this.companyCategory = companyCategory;
	}

	public Company(int companyId) {
		this.companyId = companyId;
	}

	public Company(String companyName, String uRL, String yearFounded, String city, String state, String country,
			String zipCode, String companyType, String description, CompanyCategory companyCategory) {
		this.companyName = companyName;
		URL = uRL;
		this.yearFounded = yearFounded;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.companyType = companyType;
		this.description = description;
		this.companyCategory = companyCategory;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CompanyCategory getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(CompanyCategory companyCategory) {
		this.companyCategory = companyCategory;
	}
}

