import java.time.LocalDateTime;
import java.util.ArrayList;

public class Picture implements Likeable {
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

	@Override
	public void beLiked() {
		this.likes++;
	}
	
}
