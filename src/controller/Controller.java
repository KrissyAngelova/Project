package controller;

import java.rmi.server.UID;
import java.util.List;

import model.User;
import model.dao.IUserDao;

public class Controller {

	static List<User> users;
	static IUserDao dao= IUserDao.getDAO(IUserDao.DataSource.DB);

	
	users=dao.getAllUsers();
	
	static boolean regUser(String firstName, String lastName, String email, String password){
		for(User u:users){
			if(u.getEmail().equals(email)){
				return false;
			}
		}
		User newUser= new User(firstName, lastName, email, password);
	}
}
