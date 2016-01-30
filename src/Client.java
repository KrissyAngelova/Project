import java.util.ArrayList;
import java.util.Scanner;


public class Client{
	private String username;
	private String password;
	private Album myPics;
	private Album favourites;// Пиннатите снимки
	
	private ArrayList<Picture> likedPics;// тук се пазят like-натите снимки, за да се знае, че те
	                                     //могат да се unlike-ват. Няма как да unlike-неш снимка,
										// която не си like-нал
	// същото и за like-натите коментари
	private ArrayList<Comment> likedComments;
	// и за dislike-натите коментари
	private ArrayList<Comment> dislikedComments;
	// предполагам, че като направим нещата с база данни, тия масиви ще отпаднат от кода.
	
	Client(String username, String password){
		this.username = username;
		this.password = password;
		this.myPics = new Album();
		this.favourites = new Album("Favourites");
		this.likedPics = new ArrayList();
	}
	
	String getUsername(){
		return this.username;
	}
	
	String getPass(){
		return this.password;
	}
	
	void likePicture(Picture p){
		if(p!=null){
			if(!this.likedPics.contains(p)){
				this.likedPics.add(p);
				p.beLiked();
			}
		}
	}
	
	void unlikePicture(Picture p){
		if(p!=null){
			if(this.likedPics.contains(p)){
				p.beUnliked();
				this.likedPics.remove(p);
			}
		}
	}
	
	// Добавяне към favourites
	void pinPicture(Picture p){
		if(p!=null){
			if(!this.favourites.pics.contains(p)){
				this.favourites.pics.add(p);
			}
		}
	}
	
	// Премахване от favourites
	void unpinPicture(Picture p){
		if(p!=null){
			if(this.favourites.pics.contains(p))
				this.favourites.pics.remove(p);
		}
	}
	
	void addComment(Picture p){
		if(p!=null){
			Scanner sc = new Scanner(System.in);
			String content = sc.nextLine();
			p.getCommentsArray().add(new Comment(content));
			sc.close();
		}
	}
	

    // За like-ване  на коментар	
	void likeComment(Comment c){
		if(c!=null){
			if(!this.likedComments.contains(c)){
				c.beLiked();
				this.likedComments.add(c);
			}
		}
	}
	
	// За unlike-ване на like-нат коментар
	void unlikeComment(Comment c){
		if(c!=null){
			if(this.likedComments.contains(c)){
				c.beUnliked();
				this.likedComments.remove(c);
			}
		}
	}
	
	// За dislike-ване на коментар
	void disLikeComment(Comment c){
		if(c!=null){
			if(!this.dislikedComments.contains(c)){
				c.beDisLiked();
				this.dislikedComments.add(c);
			}
		}
	}
	
	// За undislike-ване на dislike-нат коментар
	void undislikeComment(Comment c){
		if(c!=null){
			if(this.dislikedComments.contains(c)){
				c.beUndisliked();
				this.dislikedComments.add(c);
			}
		}
	}
	
	
	void showMyPics(){
		if(this.myPics.pics.isEmpty()){
			System.out.println("No pics to show");
		}
		else
			this.myPics.showAlbum();
	}
	
	void showFavourites(){
		if(this.favourites.pics.isEmpty()){
			System.out.println("No favourite pics to show");
		}
		else
			this.favourites.showAlbum();
	}
}
