package model;
import java.time.LocalDateTime;
// some comment
public class Comment{
	private LocalDateTime dateTime;
	private String content;
	private User author; //Всеки коментар си има автор
	private int likes;
	private int dislikes;
	
	Comment(String content, User author){
		this.content = content;
		this.author = author;
		this.dateTime = LocalDateTime.now();
		this.likes = 0;
		this.dislikes = 0;
	}

	// Да харесаш коментар
	public void beLiked() {
		this.likes++;
	}
	//Ако си like-нал някъв коментар и искаш да го unlike-неш
	public void beUnliked(){
		if(this.likes>0){
			this.likes--;
		}
	}
	

	// Съшото като при like, но понеже нали за разлика от снимките коментарите могат
	// да получават и негативен вот - dislike
	public void beDisLiked(){
		this.dislikes++;
	}
	public void beUndisliked(){
		if(this.dislikes>0){
			this.dislikes--;
		}
	}
	
	void showComment(){
		System.out.println(this.author.getUsername()+" says: ");
		System.out.println("----------------------");
		System.out.println(this.dateTime);
		System.out.println(this.content);
		System.out.println("Likes:"+this.likes+"      "+"Dislkes:"+this.dislikes);
		System.out.println("----------------------");
	}

}
