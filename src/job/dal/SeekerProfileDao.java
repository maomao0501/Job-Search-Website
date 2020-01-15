package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import job.model.SeekerProfile;

public class SeekerProfileDao {
	protected ConnectionManager connectionManager;

	private static SeekerProfileDao instance = null;
	protected SeekerProfileDao() {
		connectionManager = new ConnectionManager();
	}
	public static SeekerProfileDao getInstance() {
		if(instance == null) {
			instance = new SeekerProfileDao();
		}
		return instance;
	}
	

	public SeekerProfile create(SeekerProfile seekerProfile) throws SQLException {
		String insertSeekerProfile  =
				"INSERT INTO SeekerProfile(UserAccountId,Industry,CurrentSalary,SalaryUnit,Resume) " +
				"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSeekerProfile);
			insertStmt.setString(1, seekerProfile.getUserAccountId());
			insertStmt.setString(2, seekerProfile.getIndustry());
			insertStmt.setString(3, seekerProfile.getCurrentSalary());
			insertStmt.setString(4, seekerProfile.getSalaryUnit());
			insertStmt.setString(5, seekerProfile.getResume());
			insertStmt.executeUpdate();
			return seekerProfile;

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
			if(resultKey != null) {
				resultKey.close();
			}
		}		
	}
	

	public SeekerProfile getSeekerProfileById(String userAccountId) throws SQLException {
		String selectSeekerProfile = "SELECT * from  SeekerProfile where UserAccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSeekerProfile);
			selectStmt.setString(1, userAccountId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				SeekerProfile seekerProfile = new SeekerProfile();
				seekerProfile.setUserAccountId(results.getString("UserAccountId"));
				seekerProfile.setIndustry(results.getString("Industry"));
				seekerProfile.setCurrentSalary(results.getString("CurrentSalary"));
				seekerProfile.setSalaryUnit(results.getString("SalaryUnit"));
				seekerProfile.setResume(results.getString("Resume"));
				return seekerProfile;
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
	

	public SeekerProfile updateSeekerProfile(SeekerProfile seekerProfile, String currentSalary) throws SQLException {
		String updateCurrentSalary = "UPDATE SeekerProfile SET CurrentSalary=? WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCurrentSalary);
			updateStmt.setString(1, currentSalary);
			updateStmt.setString(2, seekerProfile.getUserAccountId());
			updateStmt.executeUpdate();
			
			seekerProfile.setCurrentSalary(currentSalary);
			return seekerProfile;
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
	

	public SeekerProfile delete(SeekerProfile seekerProfile) throws SQLException {
		String deleteSeekerProfile = "DELETE FROM SeekerProfile WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSeekerProfile);
			deleteStmt.setString(1, seekerProfile.getUserAccountId());
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











