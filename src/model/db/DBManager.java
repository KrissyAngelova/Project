package model.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private static DBManager uniqueInstance;

	public static final String DB_NAME = "season5_Krasiva";
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
		createDB();
	}
	
	private void createDB(){
		try(Statement stmt = connection.createStatement()){
			String sql = "CREATE DATABASE IF NOT EXISTS"+DB_NAME+";";
			stmt.executeUpdate(sql);
			sql = "USE "+DB_NAME+";";
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	/*	try {
			Statement st = connection.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'krasiva';");
			if (!rs.next()) {*/
				
				File dbSchema = new File("lib/dbSchema.txt");
				try (FileReader fr = new FileReader(dbSchema); BufferedReader br = new BufferedReader(fr);Statement st = connection.createStatement();) {
					String s;
					StringBuilder queryBuilder = new StringBuilder();
					while ((s = br.readLine()) != null) {
						queryBuilder.append(s);
					}
					String query = queryBuilder.toString();
					ResultSet rs = st.executeQuery(query);
				} catch (FileNotFoundException e) {
					System.out.println("File dbSchema is not found!");
				} catch (IOException e) {
					System.out.println("Problem with dbSchema reading!");
				//}
		//	}
		} catch (SQLException e) {
			System.out.println("Problem with DB creation!");
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
