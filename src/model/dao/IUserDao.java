package model.dao;
import java.util.List;
import User;
public interface IUserDAO {

	enum DataSource {DB, JSON};
	
	void addUser(User x);
	List<User> getAllUsers();
	
	static IUserDAO getDAO(DataSource dataSource){
		switch(dataSource){
		case DB: 
			return new DBUserDAO();
		case JSON:
			return new JSONUserDAO();
		}
		throw new IllegalArgumentException();
	}
	
}