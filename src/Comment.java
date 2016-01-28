import java.time.LocalDateTime;
// some comment
public class Comment implements Likeable {
	private LocalDateTime dateTime;
	private String content;
	private int likes;
	private int dislikes;
	
	
	Comment(String content){
		this.content = content;
		this.dateTime = LocalDateTime.now();
		this.likes = 0;
		this.dislikes = 0;
	}


	@Override
	public void beLiked() {
		this.likes++;
	}
	

	
	public void beDisLiked(){
		this.dislikes++;
	}

}
