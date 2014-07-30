package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.tourismmer.app.model.Feed;
import com.tourismmer.app.model.Msg;
import com.tourismmer.app.util.Util;

public class FeedMessageDAO extends ConnectionDAO {
	
	public static Long Insert(Feed feed, Msg message) throws SQLException
    {
		Long idFeed = null;

		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	        query = "INSERT INTO FeedMessage (fm_id, fm_fe_id, fm_us_sender, fm_text, fm_datetime) "
	        		+ " VALUES (null, ?, ?, ?, ?);";
	
	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, feed.getId(), stmt);
	        setStatement(2, message.getSender().getId(), stmt);
	        setStatement(3, message.getText(), stmt);
	        setStatement(4, Util.dateToMysql(new Date()), stmt);
        
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
