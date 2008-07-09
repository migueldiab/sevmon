package com.sabre.hdt.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.sabre.hdt.entities.Activity;
import com.sabre.hdt.persistence.DBUtil;
import com.sabre.hdt.persistence.DataSource;
import com.sabre.hdt.persistence.dao.ActivityDAO;
import com.sabre.hdt.persistence.dao.exceptions.ActivityNotFoundException;
import com.sabre.hdt.persistence.dao.exceptions.DAORuntimeException;

public class ActivityDAOImpl implements ActivityDAO {

	private static Logger logger = Logger.getLogger(ActivityDAOImpl.class
			.getName());
	private static final String TABLE_NAME = "activities";

	public void deleteActivity(String id) throws ActivityNotFoundException {
		if (id == null) {
			logger.error("id parameter must be set!");
			throw new DAORuntimeException("id parameter must be set!");
		}

		Connection conn = DataSource.getConnection();

		PreparedStatement stmtDelete = null;

		try {
			StringBuffer sbDelete = new StringBuffer();

			sbDelete.append("DELETE FROM ");
			sbDelete.append(ActivityDAOImpl.TABLE_NAME);
			sbDelete.append(" WHERE activity_id = ?");

			stmtDelete = conn.prepareStatement(sbDelete.toString());

			stmtDelete.setString(1, id);

			int rows = stmtDelete.executeUpdate();

			if (rows != 1) {
				throw new SQLException("executeUpdate return value: " + rows);
			}

		} catch (SQLException ex) {
			logger.error(ex);
			throw new DAORuntimeException(ex);
		} finally {
			DBUtil.closeStatement(stmtDelete);
			//DBUtil.closeJDBCConnection(conn);
		}
	}

	public Activity findActivityByPK(String pk)
			throws ActivityNotFoundException {
		if (pk == null || pk.trim().equals("")) {
			logger.error("Activity PK is required!");
			throw new DAORuntimeException("Activity PK is required!");
		}

		Connection conn = DataSource.getConnection();

		Activity result = null;
		ResultSet rs = null;
		PreparedStatement stmtSelect = null;

		try {
			StringBuffer sbSelect = new StringBuffer();

			sbSelect.append("SELECT * FROM ");
			sbSelect.append(ActivityDAOImpl.TABLE_NAME);
			sbSelect.append(" WHERE activity_id = ?");

			stmtSelect = conn.prepareStatement(sbSelect.toString());

			stmtSelect.setString(1, pk);

			rs = stmtSelect.executeQuery();
			if (rs.next()) {
				result = new Activity(rs.getString("activity_id"), rs
						.getString("activity_name"), rs.getString("status"), rs
						.getString("attachments"), rs.getString("email_cc"), rs
						.getString("account_location"), rs
						.getString("description"),
						rs.getString("email_sender"), rs
								.getDate("last_updated"), rs
								.getDate("planned_start"), rs
								.getDate("created"),
						rs.getInt("score"));
			} else {
				throw new ActivityNotFoundException();
			}

		} catch (SQLException ex) {
			logger.error(ex);
			throw new DAORuntimeException(ex);
		} finally {
			DBUtil.closeStatement(stmtSelect);
			DBUtil.closeResultSet(rs);
			//DBUtil.closeJDBCConnection(conn);
		}
		return result;
	}

	public void insertActivity(Activity activity) {

		PreparedStatement stmtInsert = null;

		Connection conn = DataSource.getConnection();

		try {
			StringBuffer sbInsert = new StringBuffer();
			sbInsert.append("INSERT INTO ");
			sbInsert.append(ActivityDAOImpl.TABLE_NAME);
			sbInsert
					.append(" ( activity_id, Activity_Name, status, attachments, email_cc, account_location, description, email_sender, last_updated, planned_start, created) ");
			sbInsert.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmtInsert = conn.prepareStatement(sbInsert.toString());

			stmtInsert.setString(1, activity.getActivityId());
			stmtInsert.setString(2, activity.getActivityName());
			stmtInsert.setString(3, activity.getStatus());
			stmtInsert.setString(4, activity.getAttachments());
			stmtInsert.setString(5, activity.getEmailCC());
			stmtInsert.setString(6, activity.getAccountLocation());
			stmtInsert.setString(7, activity.getDescription());
			stmtInsert.setString(8, activity.getEmailSender());
			stmtInsert.setDate(9, new java.sql.Date(activity.getLastUpdated()
					.getTime()));
			stmtInsert.setDate(10, new java.sql.Date(activity.getPlannedStart()
					.getTime()));
			stmtInsert.setDate(11, new java.sql.Date(activity.getCreated()
					.getTime()));
			stmtInsert.setInt(12, activity.getScore());


			logger.info("About to execute INSERT: values "
					+ activity.toString());

			int rows = stmtInsert.executeUpdate();

			if (rows != 1) {
				throw new SQLException("executeUpdate return value: " + rows);
			}

		} catch (SQLException ex) {
			logger.error(ex);
			throw new DAORuntimeException(ex);
		} finally {
			DBUtil.closeStatement(stmtInsert);
			//DBUtil.closeJDBCConnection(conn);
		}

	}

	public void updateActivity(Activity activity) {

		Connection conn = DataSource.getConnection();

		PreparedStatement stmtUpdate = null;

		try {
			StringBuffer sbUpdate = new StringBuffer();

			sbUpdate.append("UPDATE ");
			sbUpdate.append(ActivityDAOImpl.TABLE_NAME);
			sbUpdate.append(" SET ");
			sbUpdate.append(" Activity_Name = ?, ");
			sbUpdate.append(" status = ? ");
			sbUpdate.append(" attachments = ?, ");
			sbUpdate.append(" email_cc = ?, ");
			sbUpdate.append(" account_location = ? ");
			sbUpdate.append(" description = ?, ");
			sbUpdate.append(" email_sender = ?, ");
			sbUpdate.append(" last_updated = ? ");
			sbUpdate.append(" planned_start = ?, ");
			sbUpdate.append(" created = ?, ");
			sbUpdate.append(" score = ? ");
			sbUpdate.append(" WHERE activity_id = ?");
			stmtUpdate = conn.prepareStatement(sbUpdate.toString());

			stmtUpdate.setString(1, activity.getActivityName());
			stmtUpdate.setString(2, activity.getStatus());
			stmtUpdate.setString(3, activity.getAttachments());
			stmtUpdate.setString(4, activity.getEmailCC());
			stmtUpdate.setString(5, activity.getAccountLocation());
			stmtUpdate.setString(6, activity.getDescription());
			stmtUpdate.setString(7, activity.getEmailSender());
			stmtUpdate.setDate(8, new java.sql.Date(activity.getLastUpdated()
					.getTime()));
			stmtUpdate.setDate(9, new java.sql.Date(activity.getPlannedStart()
					.getTime()));
			stmtUpdate.setDate(10, new java.sql.Date(activity.getCreated()
					.getTime()));
			stmtUpdate.setInt(11, activity.getScore());
			stmtUpdate.setString(12, activity.getActivityId());
			int rows = stmtUpdate.executeUpdate();

			if (rows != 1) {
				throw new SQLException("executeUpdate return value: " + rows);
			}

		} catch (SQLException ex) {
			throw new DAORuntimeException(ex);
		} finally {
			DBUtil.closeStatement(stmtUpdate);
			//DBUtil.closeJDBCConnection(conn);
		}
	}

	public Collection<Activity> findAll() throws ActivityNotFoundException {

		Connection conn = DataSource.getConnection();

		Collection<Activity> result = new ArrayList<Activity>();

		ResultSet rs = null;
		PreparedStatement stmtSelect = null;

		try {
			StringBuffer sbSelect = new StringBuffer();

			sbSelect.append("SELECT * FROM ");
			sbSelect.append(ActivityDAOImpl.TABLE_NAME);

			stmtSelect = conn.prepareStatement(sbSelect.toString());
			rs = stmtSelect.executeQuery();
			while (rs.next()) {
				Activity activity = new Activity(rs.getString("activity_id"),
						rs.getString("activity_name"), rs.getString("status"),
						rs.getString("attachments"), rs.getString("email_cc"),
						rs.getString("account_location"), rs
								.getString("description"), rs
								.getString("email_sender"), rs
								.getDate("last_updated"), rs
								.getDate("planned_start"), rs
								.getDate("created"),
								rs.getInt("score"));
				result.add(activity);
				logger.debug(activity);
			}
			if (result.isEmpty()) {
				logger.info("No activities were found...");
				throw new ActivityNotFoundException();
			}
		} catch (SQLException ex) {
			logger.error(ex);
			throw new DAORuntimeException(ex);
		} finally {
			DBUtil.closeStatement(stmtSelect);
			DBUtil.closeResultSet(rs);
			//DBUtil.closeJDBCConnection(conn);
		}
		return result;
	}

}
