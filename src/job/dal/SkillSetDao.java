package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import job.model.SkillSet;

public class SkillSetDao {
	protected ConnectionManager connectionManager;

	private static SkillSetDao instance = null;
	protected SkillSetDao() {
		connectionManager = new ConnectionManager();
	}
	public static SkillSetDao getInstance() {
		if(instance == null) {
			instance = new SkillSetDao();
		}
		return instance;
	}
	
	/**
	 * Save the SkillSet instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public SkillSet create(SkillSet skillSet) throws SQLException {
		String insertSkillSet =
				"INSERT INTO UserAccount(SkillSetName) " +
				"VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSkillSet,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, skillSet.getSkillSetName());
			insertStmt.executeUpdate();
		
			resultKey = insertStmt.getGeneratedKeys();
			int skillSetId = -1;
			if(resultKey.next()) {
				skillSetId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			skillSet.setSkillSetId(skillSetId);
			
			return skillSet;
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
	 * Get the SkillSet record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single SkillSet instance.
	 */
	public SkillSet getSkillSetById(int skillSetId) throws SQLException {
		String selectSkillSet = "SELECT SkillSetId, SkillSetName " +
				"FROM UserAccount " +
				"WHERE UserAccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSkillSet);
			selectStmt.setInt(1, skillSetId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultSkillSetId = results.getInt("SkillSetId");
				String skillSetName = results.getString("SkillSetName");
				
				SkillSet skillSet = new SkillSet(resultSkillSetId, skillSetName);
				return skillSet;
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
	 * Update the content of the SkillSet instance.
	 * This runs a UPDATE statement.
	 */
	public SkillSet updateSkillSetName(SkillSet skillSet, String newSkillSetName) throws SQLException {
		String updateSkillSetName = "UPDATE SkillSet SET SkillSetName=? WHERE SkillSetId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSkillSetName);
			updateStmt.setString(1, newSkillSetName);
			updateStmt.setInt(2, skillSet.getSkillSetId());
			updateStmt.executeUpdate();
			
			skillSet.setSkillSetName(newSkillSetName);
			return skillSet;
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
	 * Delete the SkillSet instance.
	 * This runs a DELETE statement.
	 */
	public SkillSet delete(SkillSet skillSet) throws SQLException {
		String deleteSkillSet = "DELETE FROM SkillSet WHERE SkillSetId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSkillSet);
			deleteStmt.setInt(1, skillSet.getSkillSetId());
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