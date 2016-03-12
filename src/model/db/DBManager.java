package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager uniqueInstance;

	public static final String DB_NAME = "season5_java3";
	private static final String DB_URL = "jdbc:mysql://192.168.8.22:3306/" + DB_NAME;
	private static final String DB_USER = "ittstudent";
	private static final String DB_PASS = "ittstudent-123";

	private Connection connection;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("Problem with driver loading!");
		} catch (SQLException e) {
			System.out.println("Problem with connection!");
		}
	}

	public static synchronized DBManager getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new DBManager();
		return uniqueInstance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Problem with connection.close()!");
		}
	}
}
