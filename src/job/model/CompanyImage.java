package job.model;

public class CompanyImage {
	protected int imageId;
	protected byte[] companyImage;
	protected Company company;
	
	public CompanyImage(int imageId, byte[] companyImage, Company company) {
		this.imageId = imageId;
		this.companyImage = companyImage;
		this.company = company;
	}
	
	public CompanyImage(int imageId) {
		this.imageId = imageId;
	}
	
	public CompanyImage(byte[] companyImage, Company company) {
		this.companyImage = companyImage;
		this.company = company;
	}

	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public byte[] getCompanyImage() {
		return companyImage;
	}
	public void setCompanyImage(byte[] companyImage) {
		this.companyImage = companyImage;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

}

