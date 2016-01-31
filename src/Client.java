import java.util.ArrayList;
import java.util.Scanner;


public class Client{
	private String username;
	private String password;
	private PrivateAlbum myPics;
	private PrivateAlbum favourites;// Пиннатите снимки
	static Album.NewPictures newPics = Album.NewPictures.getNewPictures();
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
		this.myPics = new PrivateAlbum();
		this.favourites = new PrivateAlbum("Favourites");
		this.likedPics = new ArrayList();
	}
	
	String getUsername(){
		return this.username;
	}
	
	String getPass(){
		return this.password;
	}
	
	//При качването на снимка се създава нов обект от тип снимка с описание
	//добавя се към албума myPics, и се обновява NewPictures
	void uploadPicture(String description){
		Picture p = new Picture(description, this);
		this.myPics.addPicture(p);
	    this.newPics.addPicture(p);
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
			p.getCommentsArray().add(new Comment(content, this));
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
	
	//Всеки клиент вижда бутоните:"My Pics", "Favourites", "New Pictures", "Top Rated"
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
	
	class PrivateAlbum extends Album{ // Двата лични албума на всеки клиент са от тип вътрешен клас, 
									 //защото те не могат да бъдат създавани без клиент
		private PrivateAlbum(){
			super();
		}
		
		private PrivateAlbum(String name){
			super(name);
		}
	}
}
