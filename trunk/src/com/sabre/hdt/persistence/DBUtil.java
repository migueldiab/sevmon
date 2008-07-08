package com.sabre.hdt.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBUtil {

	public static Logger logger = Logger.getLogger(DBUtil.class.getName());

	public static void closeJDBCConnection(final Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				logger.error(conn, ex);
			}
		}
	}

	public static void closeStatement(final Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				logger.error(stmt, ex);
			}
		}
	}

	public static void closeResultSet(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.error(rs, ex);
			}
		}
	}
}
