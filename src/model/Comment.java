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

	public Comment(String content, LocalDateTime dt, int likes, int dislikes){
		this.content = content;
	//	this.author = author;
		this.dateTime = dt;
		this.likes = likes;
		this.dislikes = dislikes;
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
	
	public String getContent(){
		return this.content;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
	
	

}
