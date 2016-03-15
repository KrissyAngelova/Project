package model.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager { //TABLES CREATION does not work fine,
							// schema creation works fine
	// driver loading works fine through main()
	// DRIVER LOADING does not work fine when it is called from signUpServlet,controller,..

	private static DBManager uniqueInstance;

	public static final String DB_NAME = "krasiva";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/krasiva";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "*Gugche8";

	private Connection connection;

	public static void main(String[] args) { // TEST
		DBManager.getInstance();
	}

	private DBManager() {		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println("Problem with driver loading!");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Problem with connection!");
		}
		createDB();

	}

	private void createDB() {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt
					.executeQuery("SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'krasiva';");
			//System.out.println(1);
			if (!rs.next()) {
				//System.out.println(2);
				File dbSchema = new File("lib/dbSchema.txt");
				try (FileReader fr = new FileReader(dbSchema); BufferedReader br = new BufferedReader(fr);) {
					String s;
					StringBuilder queryBuilder = new StringBuilder();
					while ((s = br.readLine()) != null) {
						queryBuilder.append(s);
					}
					String query = queryBuilder.toString();
					stmt.executeUpdate(query); // TODO PROBLEM
												// ???!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					//System.out.println(3);
				}
				// String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME +
				// ";";
				// stmt.executeUpdate(sql);
				// // create tables
				// File dbSchema = new File("lib/dbSchema.txt");
				// try (FileReader fr = new FileReader(dbSchema);
				// BufferedReader br = new BufferedReader(fr);
				// Statement st = connection.createStatement();) {
				// System.out.println("Create statement!");
				// String s;
				// StringBuilder queryBuilder = new StringBuilder();
				// while ((s = br.readLine()) != null) {
				// queryBuilder.append(s);
				// }
				// String query = queryBuilder.toString();
				// st.executeUpdate(query); // ?? PROBLEM
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				catch (FileNotFoundException e) {
					System.out.println("File dbSchema.txt is not found!");
				} catch (IOException e) {
					System.out.println("Problem with dbSchema.txt reading!");
					// } catch (SQLException e) {
					// System.out.println("Problem with tables creation!");
					// e.printStackTrace();
					// }
				}
			}
		} catch (SQLException e) {
			System.out.println("Problem with DB creation!");
		}
	}

	public static synchronized DBManager getInstance() {
		System.out.println(5);
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
