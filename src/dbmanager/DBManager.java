package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Singleton клас, който да се казва DBManager. 
//В конструктора му трябва да се създаде Connection към базата данни, 
//като данните за връзката трябва да са статични стойности. 
//След това трябва да изпълните всички CREATE заявки към базата, 
//като заявките трябва да отговарят на вашата DB диаграма.

public class DBManager {

	private static DBManager uniqueInstance;
	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static Connection connect;
	private static String url = "jdbc:mysql://127.0.0.1:3306/season5_java3";
	private static String user = "root";
	private static String password = "pass";

	private DBManager() {
		try {
			Class.forName(driverClassName);
			System.out.println("Driver Loading SUCCESSFUL!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading NOT SUCCESSFUL!");
		}
		
		// create zaiavkite Statement st= getConnection().createStatement()
	}

	private static void getDBManager() {
		if (uniqueInstance == null) {
			uniqueInstance = new DBManager();
		}
	}

	public static Connection getConnection() {
		getDBManager();
		
		try {
			connect = DriverManager.getConnection(url, user, password);
			System.out.println("Connection SUCCESSFUL!");
		} catch (SQLException e) {
			System.out.println("Connection NOT SUCCESSFUL!");
		}
		return connect;
	}

	public static void closeConnection(Connection connect){
	try {
		connect.close();
	} catch (SQLException e) {
	}
	}
}


