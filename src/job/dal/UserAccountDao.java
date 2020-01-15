package job.dal;

import job.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDao{

	private static UserAccountDao instance = null;
	public UserAccountDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserAccountDao getInstance() {
		if(instance == null) {
			instance = new UserAccountDao();
		}
		return instance;
	}
	
	ConnectionManager connectionManager = new ConnectionManager();
	public boolean create(UserAccount userAccount) throws SQLException {
		// Insert into the superclass table first.

		String insertUserSql = "INSERT INTO useraccount(UserAccountId,UserTypeId,FirstName,LastName,Email,Password,DateOfBirth,Gender,ContactNumber,RegistrationDate) VALUES(?,?,?,?,?,?,?,?,?,now());";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserSql);
			insertStmt.setString(1, userAccount.getUserAccountId());
			insertStmt.setString(2, userAccount.getUserType().getUserTypeId());
			insertStmt.setString(3, userAccount.getFirstName());
			insertStmt.setString(4, userAccount.getLastName());
			insertStmt.setString(5, userAccount.getEmail());
			insertStmt.setString(6, userAccount.getPassword());
			insertStmt.setDate(7, new java.sql.Date(userAccount.getDateOfBirth().getTime()));
			insertStmt.setString(8, userAccount.getGender());
			insertStmt.setString(9, userAccount.getContactNumber());
						
			int r=insertStmt.executeUpdate();
			if (r==1) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	public List<UserAccount> getjobUsersFromFirstName(String firstName) throws SQLException {
		// TODO Auto-generated method stub
		String selectUserSql =
				"SELECT * from UserAccount where firstName=?"
				+ "ORDER BY UserAccountId DESC;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			List<UserAccount> userAccounts = new ArrayList<>();
			UserTypeDao userTypeDao = UserTypeDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUserSql);
				selectStmt.setString(1, firstName);
				results = selectStmt.executeQuery();
				while(results.next()) {
					UserAccount userAccount = new UserAccount();
					userAccount.setUserAccountId(results.getString("UserAccountId"));
					userAccount.setUserType(userTypeDao.getUserTypeById(results.getString("UserTypeId")));
					userAccount.setContactNumber(results.getNString("ContactNumber"));
					userAccount.setDateOfBirth(results.getDate("DateOfBirth"));
					userAccount.setEmail(results.getString("Email"));
					userAccount.setFirstName(results.getString("FirstName"));
					userAccount.setGender(results.getString("Gender"));
					userAccount.setLastName(results.getString("LastName"));
					userAccount.setPassword(results.getNString("Password"));
					userAccount.setRegistrationDate(results.getDate("RegistrationDate"));
					userAccounts.add(userAccount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return userAccounts;
	}
	public boolean delete(String userAccountId) throws SQLException {
		// TODO Auto-generated method stub
		String deleteBlogUser = "DELETE FROM UserAccount WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, userAccountId);
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
		return false;
	}
	public UserAccount getJobUserFromUserAccountId(String userAccountId) throws SQLException {
		// TODO Auto-generated method stub
		String selectUserSql =
				"SELECT * from useraccount where UserAccountId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUserSql);
				selectStmt.setString(1, userAccountId);
				results = selectStmt.executeQuery();
				if(results.next()) {
					UserAccount userAccount = new UserAccount();
					userAccount.setUserAccountId(results.getString("UserAccountId"));
					userAccount.setContactNumber(results.getNString("ContactNumber"));
					userAccount.setDateOfBirth(results.getDate("DateOfBirth"));
					userAccount.setEmail(results.getString("Email"));
					userAccount.setFirstName(results.getString("FirstName"));
					userAccount.setGender(results.getString("Gender"));
					userAccount.setLastName(results.getString("LastName"));
					userAccount.setPassword(results.getNString("Password"));
					userAccount.setRegistrationDate(results.getDate("RegistrationDate"));
						
					return userAccount;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
	}
	public UserAccount updateJobUserFromUserAccountId(UserAccount userAccount) throws SQLException {
		// TODO Auto-generated method stub
		String insertUserSql = "update useraccount set UserTypeId=?,FirstName=?,LastName=?,Email=?,Password=?,DateOfBirth=?,Gender=?,ContactNumber=? where UserAccountId=?;";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserSql);
			insertStmt.setString(9, userAccount.getUserAccountId());
			insertStmt.setString(1, userAccount.getUserType().getUserTypeId());
			insertStmt.setString(2, userAccount.getFirstName());
			insertStmt.setString(3, userAccount.getLastName());
			insertStmt.setString(4, userAccount.getEmail());
			insertStmt.setString(5, userAccount.getPassword());
			insertStmt.setDate(6, new java.sql.Date(userAccount.getDateOfBirth().getTime()));
			insertStmt.setString(7, userAccount.getGender());
			insertStmt.setString(8, userAccount.getContactNumber());
						
			int r=insertStmt.executeUpdate();
			if (r==1) {
				return userAccount;
			}
			else {
				return userAccount;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the LastName of the BlogUsers instance.
	 * This runs a UPDATE statement.
	 *//*
	public BlogUsers updateLastName(BlogUsers blogUser, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(blogUser, newLastName);
		return blogUser;
	}

	*//**
	 * Delete the BlogUsers instance.
	 * This runs a DELETE statement.
	 *//*
	public BlogUsers delete(BlogUsers blogUser) throws SQLException {
		String deleteBlogUser = "DELETE FROM BlogUsers WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, blogUser.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + blogUser.getUserName());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(blogUser);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public BlogUsers getBlogUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectBlogUser =
			"SELECT BlogUsers.UserName AS UserName, FirstName, LastName, DoB, StatusLevel " +
			"FROM BlogUsers INNER JOIN Persons " +
			"  ON BlogUsers.UserName = Persons.UserName " +
			"WHERE BlogUsers.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				BlogUsers.StatusLevel statusLevel = BlogUsers.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				BlogUsers blogUser = new BlogUsers(resultUserName, firstName, lastName, dob, statusLevel);
				return blogUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<BlogUsers> getBlogUsersFromFirstName(String firstName)
			throws SQLException {
		List<BlogUsers> blogUsers = new ArrayList<BlogUsers>();
		String selectBlogUsers =
			"SELECT BlogUsers.UserName AS UserName, FirstName, LastName, DoB, StatusLevel " +
			"FROM BlogUsers INNER JOIN Persons " +
			"  ON BlogUsers.UserName = Persons.UserName " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DoB").getTime());
				BlogUsers.StatusLevel statusLevel = BlogUsers.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				BlogUsers blogUser = new BlogUsers(userName, resultFirstName, lastName, dob, statusLevel);
				blogUsers.add(blogUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return blogUsers;
	}*/
}
