package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import job.model.WorkType;

public class WorkTypeDao {
	protected ConnectionManager connectionManager;

	private static WorkTypeDao instance = null;
	protected WorkTypeDao() {
		connectionManager = new ConnectionManager();
	}
	public static WorkTypeDao getInstance() {
		if(instance == null) {
			instance = new WorkTypeDao();
		}
		return instance;
	}
	
	/**
	 * Save the WorkType instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public WorkType create(WorkType workType) throws SQLException {
		String insertWorkType =
				"INSERT INTO WorkType(WorkType,Detail) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWorkType);
			insertStmt.setInt(1, workType.getWorkType());
			insertStmt.setString(2, workType.getDetail());
			insertStmt.executeUpdate();
			return workType;
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
	 * Get the WorkType record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single WorkType instance.
	 */
	public WorkType getWorkTypeById(int workTypeId) throws SQLException {
		String selectWorkType = "SELECT WorkType,Detail " +
				"FROM WorkType " +
				"WHERE WorkType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWorkType);
			selectStmt.setInt(1, workTypeId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultWorkType = results.getInt("WorkType");
				String detail = results.getString("Detail");
				WorkType workType = new WorkType(resultWorkType, detail);
				return workType;
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
	 * Update the content of the WorkType instance.
	 * This runs a UPDATE statement.
	 */
	public WorkType updateDetail(WorkType workType, String newDetail) throws SQLException {
		String updateWorkType = "UPDATE WorkType SET Detail=? WHERE WorkType=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateWorkType);
			updateStmt.setString(1, newDetail);
			updateStmt.setInt(2, workType.getWorkType());
			updateStmt.executeUpdate();
			
			workType.setDetail(newDetail);
			return workType;
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
	 * Delete the WorkType instance.
	 * This runs a DELETE statement.
	 */
	public WorkType delete(WorkType workType) throws SQLException {
		String deleteWorkType = "DELETE FROM WorkType WHERE WorkType=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWorkType);
			deleteStmt.setInt(1, workType.getWorkType());
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