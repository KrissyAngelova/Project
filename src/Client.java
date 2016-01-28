import java.util.ArrayList;
// Test GitHub coment
public class Client{
	private String username;
	private String password;
	private ArrayList<Picture>myPics;
	
	
	Client(String username, String password){
		this.username = username;
		this.password = password;
		this.myPics = new ArrayList<Picture>();
	}
	
	String getUsername(){
		return this.username;
	}
	
	String getPass(){
		return this.password;
	}
	
	//trying github code
	void tryThisGitHub(){
		System.out.println("GitHub e super smotan");
	}
}
