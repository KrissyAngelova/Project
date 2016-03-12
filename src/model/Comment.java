package model;
import java.time.LocalDateTime;
// some comment
public class Comment{
	private LocalDateTime dateTime;
	private String content;
	private User author; //����� �������� �� ��� �����
	private int likes;
	private int dislikes;
	
	Comment(String content, User author){
		this.content = content;
		this.author = author;
		this.dateTime = LocalDateTime.now();
		this.likes = 0;
		this.dislikes = 0;
	}

	// �� ������� ��������
	public void beLiked() {
		this.likes++;
	}
	//��� �� like-��� ����� �������� � ����� �� �� unlike-���
	public void beUnliked(){
		if(this.likes>0){
			this.likes--;
		}
	}
	

	// ������ ���� ��� like, �� ������ ���� �� ������� �� �������� ����������� �����
	// �� ��������� � ��������� ��� - dislike
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
