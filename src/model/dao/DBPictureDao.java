package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.rowset.serial.SQLOutputImpl;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import model.User;
import model.db.DBManager;

public class DBPictureDao {

	public void uploadPicture(User u, String picturePath, String pictureDescription) throws SQLException {
		// check if the current user has album myPictures in db
		Date date_time = new Date();
		java.sql.Date sql_date_time = new java.sql.Date(date_time.getTime());
		Connection connection = DBManager.getInstance().getConnection();
		Statement st = connection.createStatement();
		ResultSet rs = null;
		PreparedStatement ps = null;
		FileInputStream fis = null;
		try {
			rs = st.executeQuery(
					"SELECT album_name FROM krasiva.album WHERE album.name=\"myPictures\" AND album.userEmail=\""
							+ u.getEmail() + "\";");
			// if no, album newPictures is created for this user
			if (!rs.next()) {
				rs = st.executeQuery("INSERT INTO krasiva.album (album.name, album.userEmail) VALUES(\"myPictures\", \""
						+ u.getEmail() + "\");");
			}

			// adding the current picture in db
			File picture = new File(picturePath);
			fis = new FileInputStream(picture);
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

			// adding the current picture to album myPictures of the current
			// user
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
		} catch (SQLException | FileNotFoundException e) {
			System.out.println("Problem in uploadPicture()!");
		} finally {
			connection.close();
			rs.close();
			ps.close();
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("Problem with fis.close() in uploadPicture()!");
			}
		}
	}

	public void likePicture(User u, Long pictureID) {
		// add picture to liked pictures from this user
		String email = u.getEmail();
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("INSERT INTO krasiva.userLikedPosts (userEmail,postID) VALUES(?,?);");
			ps.setString(1, email);

			// incrementing the number of like of this picture
			st = connection.createStatement();
			rs = st.executeQuery("SELECT likes FROM krasiva.post WHERE postID=" + pictureID + ";");
			int pictureLikes = rs.getInt("likes");
			st.execute("UPDATE krasiva.post SET likes=" + (++pictureLikes) + " WHERE postID=" + pictureID + ";");
		} catch (SQLException e) {
			System.out.println("Problem in likePicture()!");
		} finally {
			try {
				connection.close();
				ps.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Problem with .close() in likePicture()!");
			}			
		}
	}

	public void unlikePicture(User u, Long pictureID) {
		Connection connection = DBManager.getInstance().getConnection();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("DELETE FROM krasiva.userLikedPosts WHERE postID=" + pictureID
					+ " AND userEmail=" + u.getEmail() + ";");
		} catch (SQLException e) {
			System.out.println("Deleting picture from likedPictures failed!");
		}
	}

	public void pinPicture(User u, Long pictureID) {
		// check if the current user has album favouritePictures in db
		Connection connection = DBManager.getInstance().getConnection();
		Statement st = null;
		Result
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT album_name FROM krasiva.album WHERE album.name=\"favouritePictures\" AND album.userEmail=\""
							+ u.getEmail() + "\";");

			// if no, album favouritePictures is created for this user
			if (!rs.next()) {
				rs = st.executeQuery(
						"INSERT INTO krasiva.album (album.name, album.userEmail) VALUES(\"favouritePictures\", \""
								+ u.getEmail() + "\");");
			}
		} catch (SQLException e) {
			System.out.println("Creating album favouritePicturees failed!");
		}

		// adding the current picture to album favouritePictures of the current
		// user
		try {
			ResultSet rs = st.executeQuery("SELECT albumID FROM krasiva.album WHERE album.userEmail=\"" + u.getEmail()
					+ "\" && album.name=\"favouritePictures\";");
			long albumID = rs.getInt("albumID");

			connection.setAutoCommit(false);
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO krasiva.albumPosts (albumID, postID) Values(?,?);");
			ps.setLong(1, albumID);
			ps.setLong(2, pictureID);
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Adding picture to album favouritePictures failed!");
		}
	}

	public void unpinPicture(User u, Long pictureID) {
		try {
			Connection connection = DBManager.getInstance().getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT albumID FROM krasiva.album WHERE album.userEmail=\"" + u.getEmail()
					+ "\" && album.name=\"favouritePictures\";");
			long albumID = rs.getInt("albumID");
			st.executeQuery(
					"DELETE FROM krasiva.albumPosts WHERE postID=" + pictureID + " AND albumID=" + albumID + ";");
			int pictureLikes = rs.getInt("likes");
		} catch (SQLException e) {
			System.out.println("Removing picture from album favouritePictures of this user failed!");
		}
	}

}
