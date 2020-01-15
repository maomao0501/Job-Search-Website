package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import job.model.Job;
import job.model.JobApplyActivity;
import job.model.UserAccount;

public class JobApplyActivityDao {
	protected ConnectionManager connectionManager;

	private static JobApplyActivityDao instance = null;
	protected JobApplyActivityDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobApplyActivityDao getInstance() {
		if(instance == null) {
			instance = new JobApplyActivityDao();
		}
		return instance;
	}
	
	/**
	 * Save the JobApplyActivity instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public JobApplyActivity create(JobApplyActivity jobApplyActivity) throws SQLException {
		String insertJobApplyActivity =
				"INSERT INTO JobApplyActivity(JobId,UserAccountId,ApplyDate) " +
				"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJobApplyActivity,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, jobApplyActivity.getJob().getJobId());
			insertStmt.setString(2, jobApplyActivity.getUserAccount().getUserAccountId());
			insertStmt.setTimestamp(3, new Timestamp(jobApplyActivity.getApplyDate().getTime()));
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int jobApplyActivityId = -1;
			if(resultKey.next()) {
				jobApplyActivityId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			jobApplyActivity.setJobApplyActivityId(jobApplyActivityId);
			return jobApplyActivity;
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
	 * Get the JobApplyActivity record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single JobApplyActivity instance.
	 */
	public JobApplyActivity getJobApplyActivityById(int jobApplyActivityId) throws SQLException {
		String selectJobApplyActivity = "SELECT JobApplyActivityId,JobId,UserAccountId,ApplyDate " +
				"FROM JobApplyActivity " +
				"WHERE JobApplyActivityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobApplyActivity);
			selectStmt.setInt(1, jobApplyActivityId);
			results = selectStmt.executeQuery();
			
			JobDao jobDao = JobDao.getInstance();
			UserAccountDao userAccountDao = UserAccountDao.getInstance();
			
			if(results.next()) {
				int resultJobApplyActivityId = results.getInt("JobApplyActivityId");
				int jobId = results.getInt("JobId");
				String userAccountId = results.getString("UserAccountId");
				Date applyDate = results.getDate("ApplyDate");
				
				Job job = jobDao.getJobById(jobId);
				UserAccount userAccount = userAccountDao.getJobUserFromUserAccountId(userAccountId);
				
				JobApplyActivity jobApplyActivity = new JobApplyActivity(resultJobApplyActivityId, job, userAccount, applyDate);
				return jobApplyActivity;
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
	 * Update the content of the JobApplyActivity instance.
	 * This runs a UPDATE statement.
	 */
	public JobApplyActivity updateJobId(JobApplyActivity jobApplyActivity, int newJobId) throws SQLException {
		String updateJobId = "UPDATE JobApplyActivity SET JobId=? WHERE JobApplyActivityId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateJobId);
			updateStmt.setInt(1, newJobId);
			updateStmt.setInt(2, jobApplyActivity.getJobApplyActivityId());
			updateStmt.executeUpdate();
			JobDao jobDao = JobDao.getInstance();
			Job job = jobDao.getJobById(newJobId);
			jobApplyActivity.setJob(job);;
			return jobApplyActivity;
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
	 * Delete the JobApplyActivity instance.
	 * This runs a DELETE statement.
	 */
	public JobApplyActivity delete(JobApplyActivity jobApplyActivity) throws SQLException {
		String deleteJobApplyActivity = "DELETE FROM JobApplyActivity WHERE JobApplyActivityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJobApplyActivity);
			deleteStmt.setInt(1, jobApplyActivity.getJobApplyActivityId());
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