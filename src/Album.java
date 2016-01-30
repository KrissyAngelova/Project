import java.util.ArrayList;

public class Album {
	protected String name;
	protected ArrayList<Picture> pics;
	protected int i = 1;
	
	Album(){
		this.name = "Album "+(this.i++);
		this.pics = new ArrayList();
	}
	
	Album(String name){
		this.name = name;
	}
	
	void setAlbumName(String name){
		this.name = name;
	}
	
	void addPicture(Picture p){
		this.pics.add(p);
	}
	
	void removePicture(Picture p){
		this.pics.remove(p);
	}
	
	void showAlbum(){
		System.out.println(this.name);
		for(Picture p : this.pics){
			if(p!=null){
				p.showPicture();
			}
		}
	}
}
