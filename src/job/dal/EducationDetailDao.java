package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import job.model.EducationDetail;

public class EducationDetailDao {
	protected ConnectionManager connectionManager;

	private static EducationDetailDao instance = null;
	protected EducationDetailDao() {
		connectionManager = new ConnectionManager();
	}
	public static EducationDetailDao getInstance() {
		if(instance == null) {
			instance = new EducationDetailDao();
		}
		return instance;
	}
	

	public EducationDetail create(EducationDetail educationDetail) throws SQLException {
		String insertEducationDetail =
				"INSERT INTO EducationDetail(UserAccountId,CertificateDegree,Major,University) " +
				"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEducationDetail);
			insertStmt.setString(1, educationDetail.getUserAccountId());
			insertStmt.setString(2, educationDetail.getCertificateDegree());
			insertStmt.setString(3, educationDetail.getMajor());
			insertStmt.setString(4, educationDetail.getUniversity());
			insertStmt.executeUpdate();
			return educationDetail;

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
	

	public EducationDetail getEducationDetailById(String userAccountId) throws SQLException {
		String selectEducationDetail = "SELECT * from  EducationDetail where UserAccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEducationDetail);
			selectStmt.setString(1, userAccountId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				EducationDetail educationDetail = new EducationDetail();
				educationDetail.setUserAccountId(results.getString("UserAccountId"));
				educationDetail.setCertificateDegree(results.getString("CertificateDegree"));
				educationDetail.setMajor(results.getString("Major"));
				educationDetail.setUniversity(results.getString("University"));
				return educationDetail;
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
	

	public EducationDetail updateCertificateDegree(EducationDetail educationDetail, String certificateDegree) throws SQLException {
		String updateCertificateDegree = "UPDATE EducationDetail SET certificateDegree=? WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCertificateDegree);
			updateStmt.setString(1, certificateDegree);
			updateStmt.setString(2, educationDetail.getUserAccountId());
			updateStmt.executeUpdate();
			
			educationDetail.setCertificateDegree(certificateDegree);
			return educationDetail;
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
	

	public EducationDetail delete(EducationDetail educationDetail) throws SQLException {
		String deleteEducationDetail = "DELETE FROM EducationDetail WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEducationDetail);
			deleteStmt.setString(1, educationDetail.getUserAccountId());
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











