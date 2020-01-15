package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import job.model.Company;
import job.model.CompanyCategory;

public class CompanyDao {
	protected ConnectionManager connectionManager;

	private static CompanyDao instance = null;
	protected CompanyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompanyDao getInstance() {
		if(instance == null) {
			instance = new CompanyDao();
		}
		return instance;
	}
	
	/**
	 * Save the Company instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Company create(Company company) throws SQLException {
		String insertCompany =
				"INSERT INTO Company(CompanyName,URL,YearFounded,City," +
				"State,Country,ZipCode,CompanyType,Description,CompanyCategoryId) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompany,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, company.getCompanyName());
			insertStmt.setString(2, company.getURL());
			insertStmt.setString(3, company.getYearFounded());
			insertStmt.setString(4, company.getCity());
			insertStmt.setString(5, company.getState());
			insertStmt.setString(6, company.getCountry());
			insertStmt.setString(7, company.getZipCode());
			insertStmt.setString(8, company.getCompanyType());
			insertStmt.setString(9, company.getDescription());
			insertStmt.setInt(10, company.getCompanyCategory().getCompanyCategoryId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int companyId = -1;
			if(resultKey.next()) {
				companyId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			company.setCompanyId(companyId);
			return company;
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
	
	/**
	 * Get the Company record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Company instance.
	 */
	public Company getCompanyById(int companyId) throws SQLException {
		String selectCompany = "SELECT CompanyId,CompanyName,URL,YearFounded,City," +
				"State,Country,ZipCode,CompanyType,Description,CompanyCategoryId " +
				"FROM Company " +
				"WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setInt(1, companyId);
			results = selectStmt.executeQuery();
			
			CompanyCategoryDao companyCategoryDao = CompanyCategoryDao.getInstance();
			
			if(results.next()) {
				int resultCompanyId = results.getInt("CompanyId");
				String companyName = results.getString("CompanyName");
				String URL = results.getString("URL");
				String yearFounded = results.getString("YearFounded");
				String city = results.getString("City");
				String state = results.getString("State");
				String country = results.getString("Country");
				String zipCode = results.getString("ZipCode");
				String companyType = results.getString("CompanyType");
				String description = results.getString("Description");
				int companyCategoryId = results.getInt("CompanyCategoryId");
			
				CompanyCategory companyCategory = companyCategoryDao.getCompanyCategoryById(companyCategoryId);
				
				Company company = new Company(resultCompanyId, companyName, URL, yearFounded, city, 
						state, country, zipCode, companyType, description, companyCategory);
				return company;
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
	 * Get the all the companies for a state.
	 */
	public List<Company> getCompanyForState(String state) throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		String selectCompanies = "SELECT CompanyId,CompanyName,URL,YearFounded,City," +
				"State,Country,ZipCode,CompanyType,Description,CompanyCategoryId " +
				"FROM Company " +
				"WHERE State=? " +
				"ORDER BY CompanyId DESC " +
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompanies);
			selectStmt.setString(1, state);
			results = selectStmt.executeQuery();
			
			CompanyCategoryDao companyCategoryDao = CompanyCategoryDao.getInstance();
			
			while(results.next()) {
				int resultCompanyId = results.getInt("CompanyId");
				String companyName = results.getString("CompanyName");
				String URL = results.getString("URL");
				String yearFounded = results.getString("YearFounded");
				String city = results.getString("City");
				String Resultstate = results.getString("State");
				String country = results.getString("Country");
				String zipCode = results.getString("ZipCode");
				String companyType = results.getString("CompanyType");
				String description = results.getString("Description");
				int companyCategoryId = results.getInt("CompanyCategoryId");
			
				CompanyCategory companyCategory = companyCategoryDao.getCompanyCategoryById(companyCategoryId);
				
				Company company = new Company(resultCompanyId, companyName, URL, yearFounded, city, 
						Resultstate, country, zipCode, companyType, description, companyCategory);
				
				companies.add(company);
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
		return companies;
	}
	
	/**
	 * Update the content of the Company instance.
	 * This runs a UPDATE statement.
	 */
	public Company updateCompanyName(Company company, String newCompanyName) throws SQLException {
		String updateCompanyName = "UPDATE Company SET CompanyName=? WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompanyName);
			updateStmt.setString(1, newCompanyName);
			updateStmt.setInt(2, company.getCompanyId());
			updateStmt.executeUpdate();
			
			company.setCompanyName(newCompanyName);
			return company;
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
	 * Delete the Company instance.
	 * This runs a DELETE statement.
	 */
	public Company delete(Company company) throws SQLException {
		String deleteCompany = "DELETE FROM Company WHERE CompanyId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
			deleteStmt.setInt(1, company.getCompanyId());
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