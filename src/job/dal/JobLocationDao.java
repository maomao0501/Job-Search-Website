package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import job.model.JobLocation;

public class JobLocationDao {
	protected ConnectionManager connectionManager;

	private static JobLocationDao instance = null;
	protected JobLocationDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobLocationDao getInstance() {
		if(instance == null) {
			instance = new JobLocationDao();
		}
		return instance;
	}
	
	/**
	 * Save the JobLocation instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public JobLocation create(JobLocation jobLocation) throws SQLException {
		String insertJobLocation =
				"INSERT INTO JobLocation(StreetAddress,City,State,Country,Zip) " +
				"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJobLocation,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, jobLocation.getStreetAddress());
			insertStmt.setString(2, jobLocation.getCity());
			insertStmt.setString(3, jobLocation.getState());
			insertStmt.setString(4, jobLocation.getCountry());
			insertStmt.setString(5, jobLocation.getZip());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int jobLocationId = -1;
			if(resultKey.next()) {
				jobLocationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			jobLocation.setJobLocationId(jobLocationId);
			return jobLocation;
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
	 * Get the JobLocation record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single JobLocation instance.
	 */
	public JobLocation getJobLocationById(int jobLocationId) throws SQLException {
		String selectJobLocation = "SELECT JobLocationId,StreetAddress,City,State,Country,Zip " +
				"FROM JobLocation " +
				"WHERE JobLocationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobLocation);
			selectStmt.setInt(1, jobLocationId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultJobLocationId = results.getInt("JobLocationId");
				String streetAddress = results.getString("StreetAddress");
				String city = results.getString("City");
				String state = results.getString("State");
				String country = results.getString("Country");
				String zip = results.getString("Zip");
				JobLocation jobLocation = new JobLocation(resultJobLocationId, streetAddress, city, state, country, zip);
				return jobLocation;
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
	 * Update the content of the JobLocation instance.
	 * This runs a UPDATE statement.
	 */
	public JobLocation updateStreetAddress(JobLocation jobLocation, String newStreetAddress) throws SQLException {
		String updateStreetAddress = "UPDATE JobLocation SET StreetAddress=? WHERE JobLocationId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStreetAddress);
			updateStmt.setString(1, newStreetAddress);
			updateStmt.setInt(2, jobLocation.getJobLocationId());
			updateStmt.executeUpdate();
			
			jobLocation.setStreetAddress(newStreetAddress);
			return jobLocation;
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
	 * Delete the JobLocation instance.
	 * This runs a DELETE statement.
	 */
	public JobLocation delete(JobLocation jobLocation) throws SQLException {
		String deleteJobLocation = "DELETE FROM JobLocation WHERE JobLocationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJobLocation);
			deleteStmt.setInt(1, jobLocation.getJobLocationId());
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
