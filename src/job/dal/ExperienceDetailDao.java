package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import job.model.ExperienceDetail;

public class ExperienceDetailDao {
	protected ConnectionManager connectionManager;

	private static ExperienceDetailDao instance = null;
	protected ExperienceDetailDao() {
		connectionManager = new ConnectionManager();
	}
	public static ExperienceDetailDao getInstance() {
		if(instance == null) {
			instance = new ExperienceDetailDao();
		}
		return instance;
	}
	

	public ExperienceDetail create(ExperienceDetail experienceDetail) throws SQLException {
		String insertExperienceDetail =
				"INSERT INTO ExperienceDetail(UserAccountId,StartDate,EndDate,JobTitle,CompanyName,WorkSiteCity,WorkSiteState,WorkSiteCountry,WorkSitePostCode,Description) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertExperienceDetail );
			insertStmt.setString(1, experienceDetail.getUserAccountId());
			insertStmt.setTimestamp(2, new Timestamp(experienceDetail.getStartDate().getTime()));
			insertStmt.setTimestamp(3, new Timestamp(experienceDetail.getEndDate().getTime()));
			insertStmt.setString(4, experienceDetail.getJobTitle());
			insertStmt.setString(5, experienceDetail.getCompanyName());
			insertStmt.setString(6, experienceDetail.getWorkSiteCity());
			insertStmt.setString(7, experienceDetail.getWorkSiteState());
			insertStmt.setString(8, experienceDetail.getWorkSiteCountry());
			insertStmt.setString(9, experienceDetail.getWorkSitePostCode());
			insertStmt.setString(9, experienceDetail.getDescription());

			insertStmt.executeUpdate();
			return experienceDetail;

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
	

	public ExperienceDetail getExperienceDetailById(String userAccountId) throws SQLException {
		String selectExperienceDetail = "SELECT * from  ExperienceDetail where UserAccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectExperienceDetail);
			selectStmt.setString(1, userAccountId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				ExperienceDetail experienceDetail = new ExperienceDetail();
				experienceDetail.setUserAccountId(results.getString("UserAccountId"));
				experienceDetail.setStartDate(results.getDate("StartDate"));
				experienceDetail.setEndDate(results.getDate("EndDate"));
				experienceDetail.setJobTitle(results.getString("JobTitle"));
				experienceDetail.setCompanyName(results.getString("CompanyName"));
				experienceDetail.setWorkSiteCity(results.getString("WorkSiteCity"));
				experienceDetail.setWorkSiteState(results.getString("WorkSiteState"));
				experienceDetail.setWorkSiteCountry(results.getString("WorkSiteCountry"));
				experienceDetail.setWorkSitePostCode(results.getString("WorkSitePostCode"));
				experienceDetail.setDescription(results.getString("Description"));
				return experienceDetail;
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
	

	public ExperienceDetail updateCertificateDegree(ExperienceDetail experienceDetail, String description) throws SQLException {
		String updateCertificateDegree = "UPDATE ExperienceDetail SET Description=? WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCertificateDegree);
			updateStmt.setString(1, description);
			updateStmt.setString(2, experienceDetail.getUserAccountId());
			updateStmt.executeUpdate();
			
			experienceDetail.setDescription(description);
			return experienceDetail;
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
	

	public ExperienceDetail delete(ExperienceDetail experienceDetail) throws SQLException {
		String deleteExperienceDetail = "DELETE FROM ExperienceDetail WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteExperienceDetail );
			deleteStmt.setString(1, experienceDetail.getUserAccountId());
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











