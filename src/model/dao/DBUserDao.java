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

	@Override
	public void addUser(User x) {
		try(PreparedStatement st = DBManager.getInstance().
					getConnection().
					prepareStatement("INSERT INTO User "
							+ "(email,firstName,lastName,password, nickName) "
							+ "VALUES (?, ?, ?, ?, ?)")) {
			
			st.setString(1, x.getEmail());
			st.setString(2, x.getFirstName());
			st.setString(3, x.getLastName());
			st.setString(4, x.getPass());
			st.setString(5, x.getNickname());
			st.execute();
			st.close();
		} catch (SQLException e) {
			System.out.println("Error executing the statement in addUser:" + e.getMessage());
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> registeredUsers = new ArrayList();
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
		}
		return registeredUsers;
	}

}
