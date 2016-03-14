package model;
import java.time.LocalDateTime;
// some comment
public class Comment{
	private LocalDateTime dateTime;
	private String content;
	private User author; //Всеки коментар си има автор
	
	
	Comment(String content, User author){
		this.content = content;
		this.author = author;
		this.dateTime = LocalDateTime.now();
		
	}

	public Comment(String content, LocalDateTime dt){
		this.content = content;
	//	this.author = author;
		this.dateTime = dt;
	}
	
	
	public String getContent(){
		return this.content;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
	
	

}
