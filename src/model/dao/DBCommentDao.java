package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import model.Comment;
import model.Picture;
import model.User;
import model.db.DBManager;

public class DBCommentDao {

	private static DBCommentDao instance;
	private DBManager manager;
	
	private DBCommentDao(){
		manager = DBManager.getInstance();
	
	}
	
	public static DBCommentDao getInstance(){
		if(instance == null)
			instance = new DBCommentDao();
		return instance;
	}
	
	public void addComment(User u, Comment com, Picture pic){
		boolean success = true;
		String query = "INSERT INTO Comment "
				+ "(content, dateTime, likes, dislikes, postID, clientEmail) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(com.getDateTime());
		
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);){
			
			manager.getConnection().setAutoCommit(false);
			st.setString(1, com.getContent());
			st.setTimestamp(2, timestamp);
			st.setInt(3, 0);
			st.setInt(4, 0);
			Statement s = manager.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT postID FROM Post"
					+ "WHERE dateTime = "+java.sql.Timestamp.valueOf(pic.getDateTime())+";");
			st.setInt(5, rs.getInt("postID"));
			st.setString(6, u.getEmail());
			st.execute();
			manager.getConnection().commit();
			s.close();
			
		} catch (SQLException e) {
			success = false;
		}
	}
	
	public ArrayList<Comment> getAllCommentsAtPost(Picture pic){
		ArrayList<Comment> comments = new ArrayList();
		
		String query1 = "SELECT postID FROM Post"
					+ "WHERE dateTime = "+java.sql.Timestamp.valueOf(pic.getDateTime())+";";
		
		try(Statement s = manager.getConnection().createStatement();){
			manager.getConnection().setAutoCommit(false);
			ResultSet rs = s.executeQuery(query1);
			int postID = rs.getInt("postID");
			
			String query2 = "SELECT content, dateTime, likes, dislikes, clientEmail FROM Comments"
					+ "WHERE postID = "+ postID+";";
			Statement st = manager.getConnection().createStatement();
			ResultSet result = st.executeQuery(query2);
			if(result == null){
				st.close();				
				return (ArrayList<Comment>) comments;
			}
			while(result.next()){
				Comment com = new Comment(result.getString("content"),result.getTimestamp("dateTime").toLocalDateTime(),
											result.getInt("likes"), result.getInt("dislikes"));
					comments.add(com);
				}
		}catch(SQLException e){
		}
		
		return comments;
	}
}
