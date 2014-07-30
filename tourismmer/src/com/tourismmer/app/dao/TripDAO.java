package com.tourismmer.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.tourismmer.app.model.Trip;

public class TripDAO  extends ConnectionDAO {
	
	public static Boolean TripAlreadySearched(String A, String K, Long idUser) throws SQLException
    {
		Boolean retorno = Boolean.FALSE;
		
		String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
			query = "SELECT * FROM Trip WHERE tr_us_id_user = ? AND tr_destination_A = ? AND tr_destination_K = ?;";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, idUser, stmt);
	        setStatement(2, A, stmt);
	        setStatement(3, K, stmt);
        
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

    public static Trip SelectLastTripByIdUser(Long idUserParam) throws SQLException
    {
    	Trip trip = new Trip();
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
    	
			query = "SELECT tr_id_trip, tr_destination, tr_status, tr_destination_A, tr_destination_K FROM "
					+ " Trip WHERE tr_us_id_user = ?;";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, idUserParam, stmt);
        
	        System.out.println(stmt.toString());
			rs = stmt.executeQuery();
        
	        while (rs.next()) {
	        	trip.setIdTrip(rs.getLong("tr_id_trip"));
	        	trip.setDestination(rs.getString("tr_destination"));
	        	trip.setStatus(rs.getLong("tr_status"));
	        	trip.setA(rs.getString("tr_destination_A"));
	        	trip.setK(rs.getString("tr_destination_K"));
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
        
        return trip;
    }

    public static Trip SelectByAK(String A, String K) throws SQLException
    {
    	Trip trip = new Trip();
    	
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	        query = "SELECT tr_id_trip, tr_destination, tr_status, tr_destination_A, tr_destination_K FROM "
	        		+ " Trip WHERE tr_destination_A = ? AND tr_destination_K = ?;";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, A, stmt);
	        setStatement(2, K, stmt);
	        
	        System.out.println(stmt.toString());
	        rs = stmt.executeQuery();

	        trip.setA(A);
	        trip.setK(K);
        
	        while (rs.next()) {
	        	trip.setIdTrip(rs.getLong("tr_id_trip"));
	        	trip.setDestination(rs.getString("tr_destination"));
	        	trip.setStatus(rs.getLong("tr_status"));
	        	trip.setA(rs.getString("tr_destination_A"));
	        	trip.setK(rs.getString("tr_destination_K"));
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
        
        return trip;
    }

    public static String Publish(Date dateTrip, Long userID, String a, String k) throws SQLException
    {
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
	        query = "UPDATE Trip SET tr_status = ?, tr_date_trip = ? "
	        		+ " WHERE tr_us_id_user = ? AND tr_destination_A = ? AND tr_destination_K = ?";
        
	        stmt = connection.prepareStatement(query);
	        setStatement(1, 2, stmt);
	        setStatement(2, dateTrip, stmt);
	        setStatement(3, userID, stmt);
	        setStatement(4, a, stmt);
	        setStatement(5, k, stmt);
        
	        System.out.println(stmt.toString());
			stmt.executeUpdate();
        
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
        
        return "";
    }


    public static Trip Insert(Trip trip, Long userID) throws SQLException
    {
    	String query = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://tourismmer.cjvmtyuing6f.sa-east-1.rds.amazonaws.com:3306/tourismmer_dev", "tourismmer", "ffbola2013");
			
			query = "INSERT INTO Trip (tr_id_trip, tr_destination, tr_us_id_user, tr_status, tr_destination_A, tr_destination_K) "
					+ " VALUES (null, ?, ?, ?, ?, ?)";
        
	        stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        setStatement(1, trip.getDestination(), stmt);
	        setStatement(2, userID, stmt);
	        setStatement(3, 1, stmt);
	        setStatement(4, trip.getA(), stmt);
	        setStatement(5, trip.getK(), stmt);
	        
	        System.out.println(stmt.toString());
			stmt.executeUpdate();
			
			ResultSet keys = stmt.getGeneratedKeys(); 
	        
			keys.next();  
			trip.setIdTrip(keys.getLong(1));  
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

        return trip;
    }

}
