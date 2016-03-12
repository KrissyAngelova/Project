package model.dao;
import java.util.List;
import model.User;

public interface IUserDao {

	enum DataSource {DB, JSON};
	
	void addUser(User x);
	List<User> getAllUsers();
	
	static IUserDao getDAO(DataSource dataSource){
		switch(dataSource){
		case DB: 
			return new DBUserDao();
		case JSON:
			return new JSONUserDao();
		}
		throw new IllegalArgumentException();
	}
	
}