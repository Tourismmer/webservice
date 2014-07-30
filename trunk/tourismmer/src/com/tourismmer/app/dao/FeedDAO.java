package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.model.Feed;
import com.tourismmer.app.model.Msg;
import com.tourismmer.app.util.Util;

public class FeedDAO extends ConnectionDAO {
	
	public static Feed selectFeedByPlace(String a, String k) throws SQLException
    {
		Feed feed = new Feed();
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
		
	        query = "SELECT fe_id, fe_location FROM Feed WHERE fe_location_A = ? AND fe_location_K = ?";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, a, stmt);
	        setStatement(2, k, stmt);
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        while (rs.next()) {
	        	feed.setLocation(rs.getString("fe_location"));
	        	feed.setId(rs.getLong("fe_id"));
	        }
        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (connection != null)
				connection.close();
		}

        return feed;
    }

    public static Feed updateFeed(String a, String k, Long lastMsgShown) throws SQLException
    {
    	Feed feed = new Feed();
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
	        
		
	        query = " SELECT fe_id, fe_location, fm_id, fm_fe_id, fm_text, fm_us_sender, us_id_fb, fm_datetime FROM Feed " + 
	        		" INNER JOIN FeedMessage ON fm_fe_id = fe_id INNER JOIN User ON us_id_user = fm_us_sender " + 
	        		" WHERE fe_location_A = ? AND fe_location_K = ? AND fm_id > ?";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, a, stmt);
	        setStatement(2, k, stmt);
	        setStatement(3, lastMsgShown, stmt);
	        
	        List<Msg> list = new ArrayList<Msg>();
	        Msg msg;
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	feed.setLocation(rs.getString("fe_location"));
	        	msg = new Msg();
	        	msg.setId(rs.getLong("fm_id"));
	        	msg.setText(rs.getString("fm_text"));
	        	msg.getSender().setFbID(rs.getLong("us_id_fb"));
	        	msg.setDate(Util.getDateMySql(rs, "fm_datetime"));
	        	list.add(msg);
	        }
	        
	        feed.setMessages(list);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (connection != null)
				connection.close();
		}

        return feed;
    }

    public static Feed getTop50Messages(String a, String k) throws SQLException
    {
    	Feed feed = new Feed();
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
	        
    	
	        query = " SELECT fe_id, fe_location, fm_id, fm_fe_id, fm_text, fm_us_sender, us_id_fb, fm_datetime " + 
	        		" FROM Feed INNER JOIN FeedMessage ON fm_fe_id = fe_id INNER JOIN User ON us_id_user = fm_us_sender WHERE fe_location_A = ? AND fe_location_K = ? order by fe_id desc";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, a, stmt);
	        setStatement(2, k, stmt);
	
	        List<Msg> list = new ArrayList<Msg>();
	        Msg msg;
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	msg = new Msg();
	        	msg.setId(rs.getLong("fm_id"));
	        	msg.setText(rs.getString("fm_text"));
	        	msg.getSender().setFbID(rs.getLong("us_id_fb"));
	        	msg.setDate(Util.getDateMySql(rs, "fm_datetime"));
	        	list.add(msg);
	        }
	
	        feed.setMessages(list);
        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (connection != null)
				connection.close();
		}

        return feed;
    }

    public static Long insert(Feed feed) throws SQLException
    {
    	Long idFeed = null;
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	    	query = "INSERT INTO Feed (fe_id, fe_location, fe_location_A, fe_location_K) VALUES (null, @fe_location, @fe_location_A, @fe_location_K);";
	        
	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, feed.getLocation(), stmt);
	        setStatement(2, feed.getA(), stmt);
	        setStatement(3, feed.getK(), stmt);
	        
	        System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			ResultSet keys = stmt.getGeneratedKeys(); 
	        
			keys.next();  
			idFeed = keys.getLong(1);  
			keys.close(); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (connection != null)
				connection.close();
		}
        
        return idFeed;
    }

}
