package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import job.model.CompanyCategory;

public class CompanyCategoryDao {
	protected ConnectionManager connectionManager;

	private static CompanyCategoryDao instance = null;
	protected CompanyCategoryDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompanyCategoryDao getInstance() {
		if(instance == null) {
			instance = new CompanyCategoryDao();
		}
		return instance;
	}
	
	/**
	 * Save the CompanyCategory instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public CompanyCategory create(CompanyCategory companyCategory) throws SQLException {
		String insertCompanyCategory =
				"INSERT INTO CompanyCategory(CompanyCategoryId,CompanyCategoryDescription) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompanyCategory,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt = connection.prepareStatement(insertCompanyCategory);
			insertStmt.setInt(1, companyCategory.getCompanyCategoryId());
			insertStmt.setString(2, companyCategory.getCompanyCategoryDescription());
			insertStmt.executeUpdate();
			return companyCategory;
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
	 * Get the CompanyCategory record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single CompanyCategory instance.
	 */
	public CompanyCategory getCompanyCategoryById(int companyCategoryId) throws SQLException {
		String selectCompanyCategory = "SELECT CompanyCategoryId,CompanyCategoryDescription " +
				"FROM CompanyCategory " +
				"WHERE CompanyCategoryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompanyCategory);
			selectStmt.setInt(1, companyCategoryId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultCompanyCategoryId = results.getInt("CompanyCategoryId");
				String companyCategoryDescription = results.getString("CompanyCategoryDescription");
				CompanyCategory companyCategory = new CompanyCategory(resultCompanyCategoryId, companyCategoryDescription);
				return companyCategory;
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
	 * Update the content of the CompanyCategory instance.
	 * This runs a UPDATE statement.
	 */
	public CompanyCategory updateCompanyCategoryDescription(CompanyCategory companyCategory, String newCompanyCategoryDescription) throws SQLException {
		String updateCompanyCategoryDescription = "UPDATE CompanyCategory SET CompanyCategoryDescription=? WHERE CompanyCategoryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompanyCategoryDescription);
			updateStmt.setString(1, newCompanyCategoryDescription);
			updateStmt.setInt(2, companyCategory.getCompanyCategoryId());
			updateStmt.executeUpdate();
			
			companyCategory.setCompanyCategoryDescription(newCompanyCategoryDescription);
			return companyCategory;
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
	 * Delete the CompanyCategory instance.
	 * This runs a DELETE statement.
	 */
	public CompanyCategory delete(CompanyCategory companyCategory) throws SQLException {
		String deleteCompanyCategory = "DELETE FROM CompanyCategory WHERE CompanyCategoryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompanyCategory);
			deleteStmt.setInt(1, companyCategory.getCompanyCategoryId());
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