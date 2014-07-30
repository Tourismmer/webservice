package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

public class UserDAO extends ConnectionDAO {
	
	public static Boolean isUserPublished(User user, String a, String k) throws SQLException
    {
		boolean retorno = Boolean.FALSE;
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
		
	        query = " SELECT us_id_user, us_id_fb, us_location, us_name, tr_status FROM User " + 
	        		" INNER JOIN Trip ON tr_us_id_user = us_id_user WHERE tr_destination_A = ? AND tr_destination_K = ? AND us_id_user = ?";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, a, stmt);
	        setStatement(2, k, stmt);
	        setStatement(3, user.getId(), stmt);
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        if (Util.getLong(rs.getLong("tr_status")) == 2)
	        {
	        	retorno = Boolean.TRUE;
	        }
	        else
	        {
	        	retorno = Boolean.FALSE;
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

    public static List<User> selectUsersByPlace(String a, String k) throws SQLException
    {
    	List<User> list = new ArrayList<User>();
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
        
	        query = "SELECT us_id_user, us_id_fb, us_location, us_name, us_email, us_birthday FROM User INNER JOIN Trip on tr_us_id_user = us_id_user " + 
	        		" WHERE tr_destination_A = ? AND tr_destination_K = ? AND tr_status = 2";
	        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, a, stmt);
	        setStatement(2, k, stmt);
	        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        User user = null;
	        
	        while (rs.next()) {
	        	user = new User();
	        	user.setLocation(rs.getString("us_location"));
	        	user.setFbID(rs.getLong("us_id_fb"));
	        	user.setName(rs.getString("us_name"));
	        	user.setEmail(rs.getString("us_email"));
	        	user.setBirthDate(Util.getDateMySql(rs, "us_birthday"));
	        	list.add(user);
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

    public static User getUserByID(Long ID) throws SQLException
    {
    	User user = null;
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			

			query = "SELECT us_id_user, us_id_fb, us_location, us_name, us_email, us_birthday FROM User WHERE us_id_user = ?";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, ID, stmt);

	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();

	        while (rs.next()) {
	        	user = new User();
	        	user.setId(rs.getLong("us_id_user"));
	        	user.setFbID(rs.getLong("us_id_fb"));
	        	user.setName(rs.getString("us_name"));
	        	user.setLocation(rs.getString("us_location"));
	        	user.setEmail(rs.getString("us_email"));
	        	user.setBirthDate(Util.getDateMySql(rs, "us_birthday"));
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

        return user;
    }

    public static User getUserByFbID(Long FbID) throws SQLException
    {
    	User user = null;
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			

			query = "SELECT us_id_user, us_id_fb, us_location, us_name, us_email, us_birthday FROM User WHERE us_id_fb = ?";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, FbID, stmt);
        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        while (rs.next()) {
	        	user = new User();
	        	user.setId(rs.getLong("us_id_user"));
	        	user.setFbID(rs.getLong("us_id_fb"));
	        	user.setName(rs.getString("us_name"));
	        	user.setLocation(rs.getString("us_location"));
	        	user.setEmail(rs.getString("us_email"));
	        	user.setBirthDate(Util.getDateMySql(rs, "us_birthday"));
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

        return user;
    }

    public static User Insert(User user) throws SQLException
    {
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
    	
	        query = "INSERT INTO User (us_id_user,us_id_fb,us_location,us_name,us_email,us_birthday) " + 
	        		" VALUES (null, ?, ?, ?, ?, ?);";
        
	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, user.getFbID(), stmt);
	        setStatement(2, user.getLocation(), stmt);
	        setStatement(3, user.getName(), stmt);
	        setStatement(4, user.getEmail(), stmt);
	        setStatement(5, Util.dateToMysql(user.getBirthDate()), stmt);
        
	        ResultSet keys = stmt.getGeneratedKeys(); 
	        
			keys.next();  
			user.setId(keys.getLong(1));  
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

        return user;
    }

    public static boolean UpdateUser(User user) throws SQLException
    {
    	boolean retorno = Boolean.FALSE;
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	    	query = "UPDATE User SET us_location = ? WHERE us_id_user = ? ";
	    	
	    	stmt = connection.prepareStatement(query);
	    	setStatement(1, user.getLocation(), stmt);
	    	setStatement(2, user.getId(), stmt);
	
	    	System.out.println(stmt.toString());
			rs = stmt.executeQuery();
	    	
			while (rs.next()) {
	        	retorno = Boolean.TRUE;
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

}
