package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import model.User;
import model.db.DBManager;

public class DBPictureDao {

	public void uploadPicture(User u, String picturePath, String pictureDescription) {
		String email = u.getEmail();
		Date date_time= new Date();
		java.sql.Date sql_date_time=new java.sql.Date(date_time.getTime());
		FileInputStream fis=null;
		PreparedStatement ps=null;
		try {
			Connection connection=DBManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT album_name FROM krasiva.album WHERE album_name=\"myPictures\" AND user_email=\"" + email
							+ "\";");
			if (!rs.next()) {
				rs = st.executeQuery("INSERT INTO krasiva.album (album_name, user_email) VALUES(\"myPictures\", \""
						+ email + "\");");
			}
			
			connection.setAutoCommit(false);
			File picture= new File(picturePath);
			fis= new FileInputStream(picture);
			ps=DBManager.getInstance().getConnection().prepareStatement("INSERT INTO krasiva.post (post_photo, post_description, post_category, post_date_time, post_likes, user_email) VALUES (?,?,?,?,?,?);");
			ps.setBinaryStream(1, fis, picture.length());
			ps.setString(2, pictureDescription);
			ps.setString(3, "FRESH");
			ps.setDate(4, sql_date_time);
			ps.setInt(5, 0);
			ps.setString(6, u.getEmail());
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Picture uploading FAILED!");
		}
		catch(FileNotFoundException e){
			System.out.println("Picture not found!");
		}
		finally{
			ps.close;
		}
	}

	public void likePicture() {
	}

	public void unlikePicture() {
	}

	public void pinPicture() {
	}

	public void unpinPicture() {
	}
}
