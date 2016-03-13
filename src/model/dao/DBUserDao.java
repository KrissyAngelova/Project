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
		return instance;
	}
	
	@Override
	public boolean addUser(User x) {
		boolean success = true;
		String query = "INSERT INTO User "
				+ "(email,firstName,lastName,password, nickName) "
				+ "VALUES (?, ?, ?, ?, ?)";
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
<<<<<<< HEAD
		return success;
=======
>>>>>>> 6686f48d5326552d898e57f6801cb88681fc5643
	}

	@Override
	public List<User> getAllUsers() throws SQLException{
		List<User> registeredUsers = new ArrayList();
<<<<<<< HEAD
		String query = "SELECT email,firstName,lastName,password, nickName"
				+ "FROM User";
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		if(result == null){
			st.close();
			return registeredUsers;
		}
		while(result.next()){
			User u = new User(result.getString("firstName"), result.getString("lastName"),
					result.getString("email"), result.getString("password"));
			registeredUsers.add(u);
=======
		try( Statement st = DBManager.getInstance().
				getConnection().createStatement()){
			ResultSet rs = st.executeQuery("SELECT email,firstName,lastName,password, nickName"
					+ "FROM User");
			while(rs.next()){
				registeredUsers.add(new User(rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"), rs.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
>>>>>>> 6686f48d5326552d898e57f6801cb88681fc5643
		}
		st.close();
		return registeredUsers;
	}

}
