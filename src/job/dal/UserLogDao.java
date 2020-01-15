package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import job.model.CompanyCategory;

public class UserLogDao{

	ConnectionManager connectionManager = new ConnectionManager();
	public boolean create(CompanyCategory companyCategory) throws SQLException {
		// Insert into the superclass table first.

		String insertUserSql = "INSERT INTO companycategory(CompanyCategoryId,CompanyCategoryDescription) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserSql);
			insertStmt.setInt(1, companyCategory.getCompanyCategoryId());
			insertStmt.setString(2, companyCategory.getCompanyCategoryDescription());
						
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
	
	public boolean delete(String companyCategoryId) throws SQLException {
		// TODO Auto-generated method stub
		String deleteBlogUser = "DELETE FROM companycategory WHERE companyCategoryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogUser);
			deleteStmt.setString(1, companyCategoryId);
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
	
	public CompanyCategory update(CompanyCategory companyCategory) throws SQLException {
		// TODO Auto-generated method stub
		String insertUserSql = "update companycategory set CompanyCategoryDescription=? where companyCategoryId=?;";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserSql);		
			insertStmt.setString(1, companyCategory.getCompanyCategoryDescription());
							
			int r=insertStmt.executeUpdate();
			if (r==1) {
				return companyCategory;
			}
			else {
				return companyCategory;
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
	
	public List<CompanyCategory> getList() throws SQLException {
		// TODO Auto-generated method stub
		String selectSql =
				"SELECT * from companycategory;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectSql);
				results = selectStmt.executeQuery();
				if(results.next()) {
					CompanyCategory companyCategory = new CompanyCategory();
					companyCategory.setCompanyCategoryId(results.getInt("companyCategoryId"));
					companyCategory.setCompanyCategoryDescription(results.getString("companyCategoryDescription"));
					
					List<CompanyCategory> categories = new ArrayList<>();
					categories.add(companyCategory);
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
}
