package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import job.model.Company;
import job.model.Job;
import job.model.JobLocation;
import job.model.UserAccount;
import job.model.WorkType;


public class JobDao {
	protected ConnectionManager connectionManager;

	private static JobDao instance = null;
	protected JobDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobDao getInstance() {
		if(instance == null) {
			instance = new JobDao();
		}
		return instance;
	}
	
	/**
	 * Save the Job instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Job create(Job job) throws SQLException {
		String insertJob =
				"INSERT INTO Job(CompanyId,MainJobTitle,DateAdvertised,JobLocationId," +
				"WorkType,Classification,JobDescription,PageURL,UserAccountId) " +
				"VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJob,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, job.getCompany().getCompanyId());
			insertStmt.setString(2, job.getMainJobTitle());
			insertStmt.setTimestamp(3, new Timestamp(job.getDateAdvertised().getTime()));
			insertStmt.setInt(4, job.getJobLocation().getJobLocationId());
			insertStmt.setInt(5, job.getWorkType().getWorkType());
			insertStmt.setString(6, job.getClassification());
			insertStmt.setString(7, job.getJobDescription());
			insertStmt.setString(8, job.getPageURL());
			insertStmt.setString(9, job.getUserAccount().getUserAccountId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int jobId = -1;
			if(resultKey.next()) {
				jobId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			job.setJobId(jobId);
			return job;
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
	 * Get the Job record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Job instance.
	 */
	public Job getJobById(int jobId) throws SQLException {
		String selectJob = "SELECT JobId,CompanyId,MainJobTitle,DateAdvertised,JobLocationId," +
				"WorkType,Classification,JobDescription,PageURL,UserAccountId " +
				"FROM Job " +
				"WHERE JobId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJob);
			selectStmt.setInt(1, jobId);
			results = selectStmt.executeQuery();
			CompanyDao companyDao = CompanyDao.getInstance();
			JobLocationDao jobLocationDao = JobLocationDao.getInstance();
			WorkTypeDao workTypeDao = WorkTypeDao.getInstance();
			UserAccountDao userAccountDao = UserAccountDao.getInstance();
			
			if(results.next()) {
				int resultJobId = results.getInt("JobId");
				int companyId = results.getInt("CompanyId");
				String mainJobTitle = results.getString("MainJobTitle");
				Date dateAdvertised = results.getDate("DateAdvertised");
				int jobLocationId = results.getInt("JobLocationId");
				int workTypeId = results.getInt("WorkType");
				String classification = results.getString("Classification");
				String jobDescription = results.getString("JobDescription");
				String pageURL = results.getString("PageURL");
				String userAccountId = results.getString("UserAccountId");
				
				Company company = companyDao.getCompanyById(companyId);
				JobLocation jobLocation = jobLocationDao.getJobLocationById(jobLocationId);
				WorkType workType = workTypeDao.getWorkTypeById(workTypeId);
				UserAccount userAccount = userAccountDao.getJobUserFromUserAccountId(userAccountId);
				
				Job job = new Job(resultJobId, company, mainJobTitle, dateAdvertised, jobLocation, 
						workType, classification, jobDescription, pageURL, userAccount);
				return job;
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
	 * Get the all the jobs for a company.
	 */
	public List<Job> getJobForCompany(Company company) throws SQLException {
		List<Job> jobs = new ArrayList<Job>();
		String selectJobs = "SELECT JobId,CompanyId,MainJobTitle,DateAdvertised,JobLocationId," +
				"WorkType,Classification,JobDescription,PageURL,UserAccountId " +
				"FROM Job " +
				"WHERE CompanyId=? " +
				"ORDER BY JobId DESC " +
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobs);
			selectStmt.setInt(1, company.getCompanyId());
			results = selectStmt.executeQuery();
			
			CompanyDao companyDao = CompanyDao.getInstance();
			JobLocationDao jobLocationDao = JobLocationDao.getInstance();
			WorkTypeDao workTypeDao = WorkTypeDao.getInstance();
			UserAccountDao userAccountDao = UserAccountDao.getInstance();
			while(results.next()) {
				int resultJobId = results.getInt("JobId");
				int companyId = results.getInt("CompanyId");
				String mainJobTitle = results.getString("MainJobTitle");
				Date dateAdvertised = results.getDate("DateAdvertised");
				int jobLocationId = results.getInt("JobLocationId");
				int workTypeId = results.getInt("WorkType");
				String classification = results.getString("Classification");
				String jobDescription = results.getString("JobDescription");
				String pageURL = results.getString("PageURL");
				String userAccountId = results.getString("UserAccountId");
				
				Company resultCompany = companyDao.getCompanyById(companyId);
				JobLocation jobLocation = jobLocationDao.getJobLocationById(jobLocationId);
				WorkType workType = workTypeDao.getWorkTypeById(workTypeId);
				UserAccount userAccount = userAccountDao.getJobUserFromUserAccountId(userAccountId);
				
				Job job = new Job(resultJobId, resultCompany, mainJobTitle, dateAdvertised, jobLocation, 
						workType, classification, jobDescription, pageURL, userAccount);
				jobs.add(job);
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
		return jobs;
	}
	
	/**
	 * Update the content of the Job instance.
	 * This runs a UPDATE statement.
	 */
	public Job updateMainJobTitle(Job job, String newMainJobTitle) throws SQLException {
		String updateMainJobTitle = "UPDATE Job SET MainJobTitle=? WHERE JobId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMainJobTitle);
			updateStmt.setString(1, newMainJobTitle);
			updateStmt.setInt(2, job.getJobId());
			updateStmt.executeUpdate();
			
			job.setJobDescription(newMainJobTitle);
			return job;
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
	 * Delete the Job instance.
	 * This runs a DELETE statement.
	 */
	public Job delete(Job job) throws SQLException {
		String deleteJob = "DELETE FROM Job WHERE JobId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJob);
			deleteStmt.setInt(1, job.getJobId());
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
