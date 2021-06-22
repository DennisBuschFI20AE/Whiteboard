package com.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;

public class Db {

private static final String sql_createTables 	= "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT, password TEXT)";
private static final String sql_insertUser 		= "INSERT INTO users ( username, password) VALUES (?,?)";
private static final String sql_selectUser 		= "SELECT * FROM users WHERE username=? AND password=?";

private java.sql.Connection connection; 
private ResultSet rs;
private PreparedStatement pstmt;

	public Db()  {
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:users.db");
			pstmt = this.connection.prepareStatement("DROP TABLE users");
			pstmt.executeUpdate();
			pstmt = this.connection.prepareStatement(sql_createTables);
			System.out.println("datenbank wurde erstellt");
			pstmt.executeUpdate();
		}
		catch (SQLException | ClassNotFoundException e) 
		{		
			e.printStackTrace();	
		}	
	}
	
	public int addUser(String username, String password) {
		
		try 
		{
			PreparedStatement pstmt = this.connection.prepareStatement(sql_insertUser);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			System.out.println("user geadded");
			pstmt.executeUpdate();
			return 0;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @return String username
	 */
	public String getUser(String username, String password) {
		try 
		{
			PreparedStatement pstmt = this.connection.prepareStatement(sql_selectUser);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			System.out.println("userSelect kommt");
			rs = pstmt.executeQuery();
			while(rs.next())
				return rs.getString("username");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection getConnection() {
		return (Connection) connection;
	}		
	
}
