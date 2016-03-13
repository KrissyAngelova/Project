package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ArrayList;

public class Picture{
	private LocalDateTime dateTime;
	private String description;
	private User author;// Всяка снимка има автор
	private Integer likes;
	private ArrayList<Comment> comments;
	private enum RatingCategory{HOT, TRENDING, FRESH};
	private RatingCategory category;
	
	Picture(String description, User author){
		this.description = description;
		this.author = author;
		this.dateTime = LocalDateTime.now();
		this.likes = 0;
		this.comments = new ArrayList<Comment>();
		this.category = RatingCategory.FRESH;
	}

	public void beLiked() {
		this.likes++;
		checkLikes();
	}
	
	public void beUnliked(){
		if(this.likes>0){
			this.likes--;
			checkLikes();
		}
	}
	
	public ArrayList<Comment> getCommentsArray(){
		return this.comments;
	}
	
	private void checkLikes(){
		if(this.likes<50){
			this.category = RatingCategory.FRESH;
		}
		else if(this.likes>=50 && this.likes<100){
			this.category = RatingCategory.TRENDING;
		}
		else this.category = RatingCategory.HOT;
	}
	

	
	Integer getLikes(){
		return this.likes;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
}
