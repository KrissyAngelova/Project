package model;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.db.DBManager;


public class User{
	private String firstName;
	private String lastName;
	private String email;//username
	private String nickname;// Formiram go ot firstName i lastName
	private String password;
	private PrivateAlbum myPics;
	private PrivateAlbum favourites;// Ïèííàòèòå ñíèìêè
	static Album.NewPictures newPics = Album.NewPictures.getNewPictures();
	private ArrayList<Picture> likedPics;// òóê ñå ïàçÿò like-íàòèòå ñíèìêè, çà äà ñå çíàå, ÷å òå
	                                     //ìîãàò äà ñå unlike-âàò. Íÿìà êàê äà unlike-íåø ñíèìêà,
										// êîÿòî íå ñè like-íàë
	// ñúùîòî è çà like-íàòèòå êîìåíòàðè
	private ArrayList<Comment> likedComments;
	// è çà dislike-íàòèòå êîìåíòàðè
	private ArrayList<Comment> dislikedComments;
	// ïðåäïîëàãàì, ÷å êàòî íàïðàâèì íåùàòà ñ áàçà äàííè, òèÿ ìàñèâè ùå îòïàäíàò îò êîäà.
	
	public User(String firstName, String lastName, String email, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.nickname = firstName+" "+lastName;
		this.password = password;
		this.myPics = new PrivateAlbum();
		this.favourites = new PrivateAlbum("Favourites");
		this.likedPics = new ArrayList();
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPass(){
		return this.password;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	//Ïðè êà÷âàíåòî íà ñíèìêà ñå ñúçäàâà íîâ îáåêò îò òèï ñíèìêà ñ îïèñàíèå
	//äîáàâÿ ñå êúì àëáóìà myPics, è ñå îáíîâÿâà NewPictures
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
	
	// Äîáàâÿíå êúì favourites
	void pinPicture(Picture p){
		if(p!=null){
			if(!this.favourites.pics.contains(p)){
				this.favourites.pics.add(p);
			}
		}
	}
	
	// Ïðåìàõâàíå îò favourites
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
	
	//Âñåêè êëèåíò âèæäà áóòîíèòå:"My Pics", "Favourites", "New Pictures", "Top Rated"
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
	
	class PrivateAlbum extends Album{ // Äâàòà ëè÷íè àëáóìà íà âñåêè êëèåíò ñà îò òèï âúòðåøåí êëàñ, 
									 //çàùîòî òå íå ìîãàò äà áúäàò ñúçäàâàíè áåç êëèåíò
		private PrivateAlbum(){
			super();
		}
		
		private PrivateAlbum(String name){
			super(name);
		}
	}
	
	
}
