package job.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import job.model.SeekerSkillSet;

public class SeekerSkillSetDao {
	protected ConnectionManager connectionManager;

	private static SeekerSkillSetDao instance = null;
	protected SeekerSkillSetDao() {
		connectionManager = new ConnectionManager();
	}
	public static SeekerSkillSetDao getInstance() {
		if(instance == null) {
			instance = new SeekerSkillSetDao();
		}
		return instance;
	}
	

	public SeekerSkillSet create(SeekerSkillSet seekerSkillSet) throws SQLException {
		String insertseekerSkillSet =
				"INSERT INTO SeekerSkillSet(SeekerSkillSetId,UserAccountId,SkillSetId,SkillLevel) " +
				"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertseekerSkillSet);
			insertStmt.setString(1, seekerSkillSet.getSeekerSkillSetId());
			insertStmt.setString(2, seekerSkillSet.getUserAccountId());
			insertStmt.setString(3, seekerSkillSet.getSkillSetId());
			insertStmt.setString(4, seekerSkillSet.getSkillLevel());
			insertStmt.executeUpdate();
			return seekerSkillSet;

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
	

	public SeekerSkillSet getSeekerSkillSetById(String userAccountId) throws SQLException {
		String selectSeekerSkillSet = "SELECT * from SeekerSkillSet where UserAccountId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSeekerSkillSet);
			selectStmt.setString(1, userAccountId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				SeekerSkillSet seekerSkillSet = new SeekerSkillSet();
				seekerSkillSet.setSeekerSkillSetId(results.getString("SeekerSkillSetId"));
				seekerSkillSet.setUserAccountId(results.getString("UserAccountId"));
				seekerSkillSet.setSkillSetId(results.getString("SkillSetId"));
				seekerSkillSet.setSkillLevel(results.getString("SkillLevel"));
				return seekerSkillSet;
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
	

	public SeekerSkillSet updateSeekerSkillSet(SeekerSkillSet seekerSkillSet, String skillLevel) throws SQLException {
		String updateSkillLevel = "UPDATE seekerSkillSet SET skillLevel=? WHERE UserAccountId=? And SkillSetId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSkillLevel);
			updateStmt.setString(1, skillLevel);
			updateStmt.setString(2, seekerSkillSet.getUserAccountId());
			updateStmt.setString(2, seekerSkillSet.getSkillSetId());
			updateStmt.executeUpdate();
			
			seekerSkillSet.setSkillLevel(skillLevel);
			return seekerSkillSet;
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
	

	public SeekerSkillSet delete(SeekerSkillSet seekerSkillSet) throws SQLException {
		String deleteSeekerSkillSet = "DELETE FROM SeekerSkillSet WHERE UserAccountId=? And SkillSetId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSeekerSkillSet);
			deleteStmt.setString(1, seekerSkillSet.getUserAccountId());
			deleteStmt.setString(2, seekerSkillSet.getSkillSetId());
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











