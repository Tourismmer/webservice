package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.model.Chat;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Numeros;

public class ChatDAO extends ConnectionDAO {
	
	public static boolean userAlreadyConnected(Long userID, Long friendID) throws SQLException
    {
		
		boolean retorno = false;
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
        
			query = "SELECT ch_id FROM Chat WHERE (ch_us_1 = @user OR ch_us_1 = ?) AND (ch_us_2 = ? OR ch_us_2 = ?)";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, userID, stmt);
	        setStatement(2, friendID, stmt);
	        setStatement(3, userID, stmt);
	        setStatement(4, friendID, stmt);
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	retorno = true;
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
        
        return retorno;
        
    }
	
	public static Long selectChatByIDs(Long userID, Long friendID) throws SQLException
    {
		Long idChat = Numeros.ZERO_L;
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
	        
			query = "SELECT ch_id, ch_us_1, ch_us_2 FROM Chat WHERE (ch_us_1 = ? OR ch_us_1 = ?) AND (ch_us_2 = ? OR ch_us_2 = ?)";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, userID, stmt);
	        setStatement(2, friendID, stmt);
	        setStatement(3, userID, stmt);
	        setStatement(4, friendID, stmt);
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	idChat =  rs.getLong("ch_id");
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

        return idChat;
    }
	
	public static List<Chat> select(Long userID) throws SQLException
    {
		
		List<Chat> chatList = new ArrayList<Chat>();
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
		
	        query = " SELECT us_id_user, us_name, us_id_fb, us_location, ch_id, ch_us_1, ch_us_2 FROM "+
	        		" Chat INNER JOIN User ON us_id_user = ch_us_1 OR us_id_user = ch_us_2 " +
	        		" WHERE (ch_us_1 = ? OR ch_us_2 = ?) AND us_id_user != ?";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, userID, stmt);
	        setStatement(2, userID, stmt);
	        setStatement(3, userID, stmt);
	
	        Chat chat;
	        User friend;
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	 chat = new Chat();
	        	 chat.setId(rs.getLong("ch_id"));
	        	 friend = new User();
	        	 friend.setId(rs.getLong("us_id_user"));
	        	 friend.setFbID(rs.getLong("us_id_fb"));
	        	 friend.setLocation(rs.getString("us_location"));
	        	 friend.setName(rs.getString("us_name"));
	        	 chat.setReceiver(friend);
	             chatList.add(chat);
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
	        
        return chatList;
        
    }

    public static Long insert(Long IDUser1, Long IDUser2) throws SQLException
    {
    	Long idReturn = Numeros.ZERO_L;
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
    	
	        query = "INSERT INTO Chat(ch_id, ch_us_1, ch_us_2) VALUES (null, ?, ?);";
	        
	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, IDUser1, stmt);
	        setStatement(2, IDUser2, stmt);
	        
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
