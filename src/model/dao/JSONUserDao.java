package model.dao;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.User;
import org.json.*;

// ne znam dali shte go polzvame, zatiova getAllUser go ostavqm neimplementiran za sega
class JSONUserDao implements IUserDao{

	@Override
	public boolean addUser(User x){
		boolean success = true;
		JSONObject user = new JSONObject();
		try{
			user.put("email", x.getEmail());
			user.put("password", x.getPass());
			user.put("firstName", x.getFirstName());
			user.put("lastName", x.getLastName());
			user.put("nickName", x.getNickname());
			File file = new File("user.json");
			FileWriter fr = new FileWriter(file);
			fr.write(user.toString());
			fr.close();
		}catch(JSONException | IOException e){
			success = false;
		}
		return success;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}