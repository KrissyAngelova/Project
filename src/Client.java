import java.util.ArrayList;
import java.util.Scanner;


public class Client{
	private String username;
	private String password;
	private Album myPics;
	private Album favourites;// ��������� ������
	
	private ArrayList<Picture> likedPics;// ��� �� ����� like-������ ������, �� �� �� ����, �� ��
	                                     //����� �� �� unlike-���. ���� ��� �� unlike-��� ������,
										// ����� �� �� like-���
	// ������ � �� like-������ ���������
	private ArrayList<Comment> likedComments;
	// � �� dislike-������ ���������
	private ArrayList<Comment> dislikedComments;
	// �����������, �� ���� �������� ������ � ���� �����, ��� ������ �� �������� �� ����.
	
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
		if(!this.likedPics.contains(p)){
			this.likedPics.add(p);
			p.beLiked();
		}
	}
	
	void unlikePicture(Picture p){
		if(this.likedPics.contains(p)){
			p.beUnliked();
			this.likedPics.remove(p);
		}
	}
	
	// �������� ��� favourites
	void pinPicture(Picture p){
		if(!this.favourites.pics.contains(p)){
			this.favourites.pics.add(p);
		}
	}
	
	// ���������� �� favourites
	void unpinPicture(Picture p){
		if(this.favourites.pics.contains(p))
			this.favourites.pics.remove(p);
	}
	
	void addComment(Picture p){
		Scanner sc = new Scanner(System.in);
		String content = sc.nextLine();
		p.getCommentsArray().add(new Comment(content));
		sc.close();
	}
	
	//TODO: ������� �� like,unlike, dislike, undislike �� ���������
	
	
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
