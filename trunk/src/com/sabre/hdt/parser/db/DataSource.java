package com.sabre.hdt.parser.db;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

	private static ThreadLocal<Connection> dbConnection = new ThreadLocal<Connection> (){
		public Connection initialValue() {
			return new DataSource().createConnection();
		}
	};

	private Properties databaseProperties = null;
	private final static String URL = "dburl"; // jdbc:mysql://localhost:3306/
												// database
	private final static String USER_NAME = "dbuser";
	private final static String PASS = "dbpassword";
	private final static String DRIVER_CLASS = "driver"; //com.mysql.jdbc.Driver
															// ,
															// com.postgres.jdbc
															// .Driver

	private DataSource() {
		try {
			readConfig();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void readConfig() throws IOException {
		// properties in the classpath
		this.databaseProperties = new Properties();
		URL url = ClassLoader.getSystemResource("database.properties");
		this.databaseProperties.load(url.openStream());
		/*
		 * If I were to read it from startup directory
		 * 
		 * java.util.Properties props = new java.util.Properties(); String path
		 * = getClass().getProtectionDomain().getCodeSource().
		 * getLocation().toString().substring(6); java.io.FileInputStream fis =
		 * new java.io.FileInputStream (new java.io.File( path +
		 * "\\myprops.props")); props.load(fis); System.out.println(props);
		 */
	}

	private Connection createConnection() {

		Connection conn = null;
		try {
			Class.forName(this.databaseProperties
					.getProperty(DataSource.DRIVER_CLASS));
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			conn = DriverManager.getConnection(this.databaseProperties
					.getProperty(DataSource.URL), this.databaseProperties
					.getProperty(DataSource.USER_NAME), this.databaseProperties
					.getProperty(DataSource.PASS));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {
		return dbConnection.get();
	}
}