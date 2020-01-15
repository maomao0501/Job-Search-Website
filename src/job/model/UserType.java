package job.model;

public class UserType {
	protected String userTypeId;
	protected String userTypeName;
	
	public UserType(String userTypeId, String userTypeName) {
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
	}

	public UserType(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	
}

