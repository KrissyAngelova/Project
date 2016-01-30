import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ArrayList;

public class Picture{
	private LocalDateTime dateTime;
	private String description;
	private int likes;
	private ArrayList<Comment> coments;
	
	Picture(String description){
		this.description = description;
		this.dateTime = LocalDateTime.now();
		this.likes = 0;
		this.coments = new ArrayList<Comment>();
	}

	public void beLiked() {
		this.likes++;
	}
	
	public void beUnliked(){
		if(this.likes>0){
			this.likes--;
		}
	}
	
	public ArrayList<Comment> getCommentsArray(){
		return this.coments;
	}
	
	void showPicture(){
		System.out.println("-------------");
		System.out.println("***Picture***");
		System.out.println(dateTime+" --- Likes:"+this.likes);
		System.out.println("-------------");
	}
}
