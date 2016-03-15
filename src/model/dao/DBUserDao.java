package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.db.DBManager;

public class DBUserDao implements IUserDao{
 
	private static DBUserDao instance;
	private DBManager manager;
	
	private DBUserDao(){
		manager = DBManager.getInstance();
	//	System.out.println("db user dao init");
	}
	
	public static DBUserDao getInstance(){
		if(instance == null)
			instance = new DBUserDao();
		System.out.println("DBUSER GET INST");
		return instance;
	}
	
	@Override
	public boolean addUser(User x) {
		boolean success = true;
		String query = "INSERT INTO User "
				+ "(email,firstName,lastName,password, nickName) "
				+ "VALUES (?, ?, ?, ?, ?);";
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);){
			
			st.setString(1, x.getEmail());
			st.setString(2, x.getFirstName());
			st.setString(3, x.getLastName());
			st.setString(4, x.getPass());
			st.setString(5, x.getNickname());
			st.execute();
		} catch (SQLException e) {
			success = false;
		}

		return success;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		List<User> registeredUsers = new ArrayList();
		try{
System.out.println("getUSers");
		String query = "SELECT email,firstName,lastName,password, nickName "
				+ "FROM krasiva.user;";
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		System.out.println("query");
		if(result == null){
			st.close();				
			return (ArrayList<User>) registeredUsers;
		}
		while(result.next()){
			User u = new User(result.getString("firstName"), result.getString("lastName"),						
					result.getString("email"), result.getString("password"));
				registeredUsers.add(u);
			}
		
		}catch(SQLException e){
			System.out.println("Problem getUSers()!");
		}
		return (ArrayList<User>) registeredUsers;

		
	}
}
