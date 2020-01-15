package job.model;

public class WorkType {
	 protected int workType;
	 protected String detail;
	 
	public WorkType(int workType, String detail) {
		this.workType = workType;
		this.detail = detail;
	}

	public WorkType(int workType) {
		super();
		this.workType = workType;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	} 
}
