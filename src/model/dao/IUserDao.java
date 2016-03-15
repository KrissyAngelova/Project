package model.dao;
import java.sql.SQLException;
import java.util.List;
import model.User;

public interface IUserDao {

	enum DataSource {DB, JSON};
	
	boolean addUser(User x);
	List<User> getAllUsers() throws SQLException;
	
	public static IUserDao getDAO(DataSource dataSource){
		switch(dataSource){
		case DB: 
			System.out.println("IDAO");
			return DBUserDao.getInstance();
		case JSON:
			return new JSONUserDao();
		}
		throw new IllegalArgumentException();
	}
	
	public static IUserDao getDAO(){
		System.out.println(3);
			return DBUserDao.getInstance();
	}
	
}