package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import job.model.Job;
import job.model.JobPostSkillSet;
import job.model.SkillSet;

public class JobPostSkillSetDao {
	protected ConnectionManager connectionManager;

	private static JobPostSkillSetDao instance = null;
	protected JobPostSkillSetDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobPostSkillSetDao getInstance() {
		if(instance == null) {
			instance = new JobPostSkillSetDao();
		}
		return instance;
	}
	
	/**
	 * Save the JobPostSkillSet instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public JobPostSkillSet create(JobPostSkillSet jobPostSkillSet) throws SQLException {
		String insertJobPostSkillSet =
				"INSERT INTO JobPostSkillSet(JobId,SkillSetId,SkillLevel) " +
				"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJobPostSkillSet,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, jobPostSkillSet.getJob().getJobId());
			insertStmt.setInt(2, jobPostSkillSet.getSkillSet().getSkillSetId());
			insertStmt.setString(3, jobPostSkillSet.getSkillLevel());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int jobPostSkillSetId = -1;
			if(resultKey.next()) {
				jobPostSkillSetId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			jobPostSkillSet.setJobPostSkillSetId(jobPostSkillSetId);
			return jobPostSkillSet;
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
	 * Get the JobPostSkillSet record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single JobPostSkillSet instance.
	 */
	public JobPostSkillSet getJobPostSkillSetById(int jobPostSkillSetId) throws SQLException {
		String selectJobPostSkillSet = "SELECT JobPostSkillSetId,JobId,SkillSetId,SkillLevel " +
				"FROM JobPostSkillSet " +
				"WHERE JobPostSkillSetId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobPostSkillSet);
			selectStmt.setInt(1, jobPostSkillSetId);
			results = selectStmt.executeQuery();
			SkillSetDao skillSetDao = SkillSetDao.getInstance();
			JobDao jobDao = JobDao.getInstance();
			
			if(results.next()) {
				int resultJobPostSkillSetId = results.getInt("JobPostSkillSetId");
				int jobId = results.getInt("JobId");
				int skillSetId = results.getInt("SkillSetId");
				String skillLevel = results.getString("SkillLevel");
				SkillSet skillSet = skillSetDao.getSkillSetById(skillSetId);
				Job job = jobDao.getJobById(jobId);
				JobPostSkillSet jobPostSkillSet = new JobPostSkillSet(resultJobPostSkillSetId, job, skillSet, skillLevel);
				return jobPostSkillSet;
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
	 * Update the content of the JobPostSkillSet instance.
	 * This runs a UPDATE statement.
	 */
	public JobPostSkillSet updateSkillLevel(JobPostSkillSet jobPostSkillSet, String newSkillLevel) throws SQLException {
		String updateSkillLevel = "UPDATE JobPostSkillSet SET SkillLevel=? WHERE JobPostSkillSetId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSkillLevel);
			updateStmt.setString(1, newSkillLevel);
			updateStmt.setInt(2, jobPostSkillSet.getJobPostSkillSetId());
			updateStmt.executeUpdate();
			
			jobPostSkillSet.setSkillLevel(newSkillLevel);
			return jobPostSkillSet;
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
	 * Delete the JobPostSkillSet instance.
	 * This runs a DELETE statement.
	 */
	public JobPostSkillSet delete(JobPostSkillSet jobPostSkillSet) throws SQLException {
		String deleteJobPostSkillSet = "DELETE FROM JobPostSkillSet WHERE JobPostSkillSetId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJobPostSkillSet);
			deleteStmt.setInt(1, jobPostSkillSet.getJobPostSkillSetId());
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
