package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import job.model.UserType;

public class UserTypeDao {
	protected ConnectionManager connectionManager;

	private static UserTypeDao instance = null;
	protected UserTypeDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserTypeDao getInstance() {
		if(instance == null) {
			instance = new UserTypeDao();
		}
		return instance;
	}
	
	/**
	 * Save the UserTypeDao instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public UserType create(UserType userType) throws SQLException {
		String insertUserType =
				"INSERT INTO UserType(UserTypeId,UserTypeName) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserType);
			insertStmt.setString(1, userType.getUserTypeId());
			insertStmt.setString(2, userType.getUserTypeName());
			insertStmt.executeUpdate();
			return userType;
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
	 * Get the UserType record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single UserType instance.
	 */
	public UserType getUserTypeById(String userTypeId) throws SQLException {
		String selectWorkType = "SELECT UserTypeId,UserTypeName " +
				"FROM UserType " +
				"WHERE UserTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkType);
			selectStmt.setString(1, userTypeId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultUserTypeId = results.getString("UserTypeId");
				String userTypeName = results.getString("UserTypeName");
				UserType userType = new UserType(resultUserTypeId, userTypeName);
				return userType;
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
	
	/**
	 * Update the content of the UserType instance.
	 * This runs a UPDATE statement.
	 */
	public UserType updateDetail(UserType userType, String newUserTypeName) throws SQLException {
		String updateUserTypeName = "UPDATE UserType SET UserTypeName=? WHERE UserTypeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserTypeName);
			updateStmt.setString(1, newUserTypeName);
			updateStmt.setString(2, userType.getUserTypeId());
			updateStmt.executeUpdate();
			
			userType.setUserTypeName(newUserTypeName);
			return userType;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Delete the UserType instance.
	 * This runs a DELETE statement.
	 */
	public UserType delete(UserType userType) throws SQLException {
		String deleteUserType = "DELETE FROM UserType WHERE UserTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserType);
			deleteStmt.setString(1, userType.getUserTypeId());
			deleteStmt.executeUpdate();
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
}