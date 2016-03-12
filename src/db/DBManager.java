package db;

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

	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String dbURL = "jdbc:mysql://127.0.0.1:3306/KrasiVa";
	private static final String dbUser = "root";
	private static final String dbPass = "*Gugche8";

	private Connection connection;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loading SUCCESSFUL!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading NOT SUCCESSFUL!");
		}

		try {
			connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
			System.out.println("Connection SUCCESSFUL!");
		} catch (SQLException e) {
			System.out.println("Connection NOT SUCCESSFUL!");
		}
	}
	
	static synchronized DBManager getDBManager(){
		if(uniqueInstance==null){
			uniqueInstance= new DBManager();
		}
		return uniqueInstance;
	}
	
	Connection getConnection(){
		return this.connection;
	}
	
	void closeConnection(){
		try{
			this.connection.close();
		}
		catch(SQLException e){
			System.out.println();
		}
	}
}
