package model;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Album { // За да не се създават просто албуми
	protected String name;
	protected ArrayList<Picture> pics;

	Album(){
		this.name = "MyAlbum ";
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
		if(this.pics.contains(p))
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
	
	static class NewPictures extends Album{ //Singleton
		private ArrayList<Picture> pics;
		private static NewPictures uniqueInstance;

		private NewPictures() {
			this.pics = new ArrayList<Picture>();
		}

		static NewPictures getNewPictures() {
			if (uniqueInstance == null) {
				uniqueInstance = new NewPictures();
			}
			return uniqueInstance;
		}
		
	}
	static class TopRatedPictures extends Album{ // Singleton
		private ArrayList<Picture>pics;
		private static TopRatedPictures uniqueInstance;
		
		private TopRatedPictures(){
			this.pics = new ArrayList();
		}
		
		static TopRatedPictures getTopRatedPics(){
			if(uniqueInstance == null){
				uniqueInstance = new TopRatedPictures();
			}
			return uniqueInstance;
		}
		
		@Override
		void addPicture(Picture p){//Добавя снимка и се сортира по боя лайкове
			super.addPicture(p);
			Collections.sort(this.pics,(p1,p2)->p1.getLikes().compareTo(p2.getLikes()));
		}
	}
	
		
}
