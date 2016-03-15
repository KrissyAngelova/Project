package controller;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UID;
import java.sql.SQLException;

import model.User;
import model.dao.IUserDao;

public class Controller {// pri signUp vika model (DBUserDAO) da mu varne spisak s
							// users, ako ima user sas sashtia email return false, ako niama
							// sazdava user i vika DAO da dobavi noviat user v
							// db

	static boolean signUpUser(String firstName, String lastName, String email, String password) {
		System.out.println(2);
		IUserDao dao = IUserDao.getDAO();
		List<User> users=null;
		try {
			users = dao.getAllUsers();
		} catch (SQLException e) {
		}
		if( users != null ){
		for (User u : users) {
			if (u.getEmail().equals(email)) {
				return false;
			}
		}
		}
		User newUser = new User(firstName, lastName, email, password);
		System.out.println(2);
		dao.addUser(newUser);
		return true;
	}
}
