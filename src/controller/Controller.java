package controller;

import java.rmi.server.UID;
import java.util.List;

import model.User;
import model.dao.IUserDao;

public class Controller {// pri signUp vika model (DBUserDAO) da mu varne spisak s
							// users, ako ima user sas sashtia email return false, ako niama
							// sazdava user i vika DAO da dobavi noviat user v
							// db

	static List<User> users;
	static IUserDao dao= IUserDao.getDAO(IUserDao.DataSource.DB);

	users=dao.getAllUsers();

	static boolean signUpUser(String firstName, String lastName, String email, String password) {
		for (User u : users) {
			if (u.getEmail().equals(email)) {
				return false;
			}
		}
		User newUser = new User(firstName, lastName, email, password);
		dao.addUser(newUser);
	}
}
