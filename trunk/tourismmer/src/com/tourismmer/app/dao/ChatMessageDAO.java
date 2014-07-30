package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tourismmer.app.model.Msg;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Numeros;
import com.tourismmer.app.util.Util;

public class ChatMessageDAO extends ConnectionDAO {
	
	public static List<Msg> getTop50Messages(Long chatID, Long lastMsgId) throws SQLException
    {
		List<Msg> list = new ArrayList<Msg>();
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	        query = "SELECT cm_id, cm_ch_id, cm_text, cm_datetime, cm_sender_id FROM ChatMessage " + 
	        		" WHERE cm_ch_id = ? AND cm_id > ? ORDER BY cm_id ASC LIMIT 10";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, chatID, stmt);
	        setStatement(2, lastMsgId, stmt);
        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        Msg msg;
	        while (rs.next()) {
	        	msg = new Msg();
	        	msg.setId(rs.getLong("cm_id"));
	        	msg.setChatId(rs.getLong("cm_ch_id"));
	        	msg.setText(rs.getString("cm_text"));
	        	msg.setSender(new User());
	        	msg.getSender().setId(rs.getLong("cm_sender_id"));
	        	msg.setDate(Util.getDateMySql(rs, "cm_datetime"));
	        	
	        	list.add(msg);
	        	
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

        return list;
    }

    public static Long insert(Msg message) throws SQLException
    {
    	Long idReturn = Numeros.ZERO_L;
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
    	
			query = "INSERT INTO ChatMessage (cm_id, cm_ch_id, cm_text, cm_datetime, cm_sender_id) VALUES (null, ?, ?, ?, ?);";

	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, message.getChatId(), stmt);
	        setStatement(2, message.getText(), stmt);
	        setStatement(2, Util.dateToMysql(new Date()), stmt);
	        setStatement(2, message.getSender().getId(), stmt);

	        System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			ResultSet keys = stmt.getGeneratedKeys(); 
	        
			keys.next();  
			idReturn = keys.getLong(1);  
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
        
        return idReturn;
    }

}
