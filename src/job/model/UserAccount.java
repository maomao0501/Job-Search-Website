package job.model;

import java.util.Date;

public class UserAccount {
	protected String userAccountId;
	protected UserType userType;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected Date dateOfBirth;
	protected String gender;
	protected String contactNumber;
	protected Date registrationDate;
	
	public UserAccount(String userAccountId, UserType userType, String firstName, String lastName, String email,
			String password, Date dateOfBirth, String gender, String contactNumber, Date registrationDate) {
		this.userAccountId = userAccountId;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.registrationDate = registrationDate;
	}
		

	public UserAccount() {
		super();
	}


	public UserAccount(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
