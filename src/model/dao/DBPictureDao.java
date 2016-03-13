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

	public void uploadPicture(User u, String picturePath, String pictureDescription) throws SQLException {
		String email = u.getEmail();
		Date date_time = new Date();
		java.sql.Date sql_date_time = new java.sql.Date(date_time.getTime());
		Connection connection = DBManager.getInstance().getConnection();

		// check if the current user has album myPictures in db
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(
				"SELECT album_name FROM krasiva.album WHERE album.name=\"myPictures\" AND album.userEmail=\"" + email
						+ "\";");
		// if no, album newPictures is created for this user
		if (!rs.next()) {
			rs = st.executeQuery("INSERT INTO krasiva.album (album.name, album.userEmail) VALUES(\"myPictures\", \""
					+ email + "\");");
		}

		// adding the current picture in db

		File picture = new File(picturePath);
		PreparedStatement ps = null;
		try {
			FileInputStream fis = new FileInputStream(picture);
			connection.setAutoCommit(false);
			ps = DBManager.getInstance().getConnection().prepareStatement(
					"INSERT INTO krasiva.post (post_photo, post_description, post_category, post_date_time, post_likes, user_email) VALUES (?,?,?,?,?,?);");
			ps.setBinaryStream(1, fis, picture.length());
			ps.setString(2, pictureDescription);
			ps.setString(3, "FRESH");
			ps.setDate(4, sql_date_time);
			ps.setInt(5, 0);
			ps.setString(6, u.getEmail());
			ps.executeUpdate();
			connection.commit();
		} catch (FileNotFoundException e) {
			System.out.println("Picture not found!");
		} catch (SQLException e) {
			System.out.println("Picture uploading FAILED!");
		}

		// adding the current picture to album myPictures of the current user
		rs = st.executeQuery("SELECT postID FROM krasiva.post WHERE post.dateTime='" + sql_date_time
				+ "' AND post.userEmail=\"" + u.getEmail() + "\";");
		long postID = rs.getInt("postID");

		rs = st.executeQuery("SELECT albumID FROM krasiva.album WHERE album.userEmail=\"" + u.getEmail()
				+ "\" && album.name=\"myPictures\";");
		long albumID = rs.getInt("albumID");

		connection.setAutoCommit(false);
		ps = connection.prepareStatement("INSERT INTO krasiva.albumPosts (albumID, postID) Values(?,?);");
		ps.setLong(1, albumID);
		ps.setLong(2, postID);
		ps.executeUpdate();
		connection.commit();
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
