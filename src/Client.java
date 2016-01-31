import java.util.ArrayList;
import java.util.Scanner;


public class Client{
	private String username;
	private String password;
	private PrivateAlbum myPics;
	private PrivateAlbum favourites;// ��������� ������
	static Album.NewPictures newPics = Album.NewPictures.getNewPictures();
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
	
	//��� ��������� �� ������ �� ������� ��� ����� �� ��� ������ � ��������
	//������ �� ��� ������ myPics, � �� �������� NewPictures
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
	
	// �������� ��� favourites
	void pinPicture(Picture p){
		if(p!=null){
			if(!this.favourites.pics.contains(p)){
				this.favourites.pics.add(p);
			}
		}
	}
	
	// ���������� �� favourites
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
	

    // �� like-����  �� ��������	
	void likeComment(Comment c){
		if(c!=null){
			if(!this.likedComments.contains(c)){
				c.beLiked();
				this.likedComments.add(c);
			}
		}
	}
	
	// �� unlike-���� �� like-��� ��������
	void unlikeComment(Comment c){
		if(c!=null){
			if(this.likedComments.contains(c)){
				c.beUnliked();
				this.likedComments.remove(c);
			}
		}
	}
	
	// �� dislike-���� �� ��������
	void disLikeComment(Comment c){
		if(c!=null){
			if(!this.dislikedComments.contains(c)){
				c.beDisLiked();
				this.dislikedComments.add(c);
			}
		}
	}
	
	// �� undislike-���� �� dislike-��� ��������
	void undislikeComment(Comment c){
		if(c!=null){
			if(this.dislikedComments.contains(c)){
				c.beUndisliked();
				this.dislikedComments.add(c);
			}
		}
	}
	
	//����� ������ ����� ��������:"My Pics", "Favourites", "New Pictures", "Top Rated"
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
	
	class PrivateAlbum extends Album{ // ����� ����� ������ �� ����� ������ �� �� ��� �������� ����, 
									 //������ �� �� ����� �� ����� ��������� ��� ������
		private PrivateAlbum(){
			super();
		}
		
		private PrivateAlbum(String name){
			super(name);
		}
	}
}
