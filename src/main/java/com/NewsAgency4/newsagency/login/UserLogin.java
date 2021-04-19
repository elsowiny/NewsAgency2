package com.NewsAgency4.newsagency.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.NewsAgency4.newsagency.DBConnection;
import com.NewsAgency4.newsagency.NewsAgency;
import com.NewsAgency4.newsagency.Subscriber;

public class UserLogin extends DBLogin{

	String username;
	String password;
	DBConnection conn = new DBConnection();
	int id;
	public Subscriber createLoginSession(String username, String password) {
		
		//create an obj that we pull all data from sql table to recreate
		//rather than persisting the obj itself in hibernate, because the tables get too complex
		//with the FK, and joins and using hibernate
		
		Connection con = conn.connectDB();
		try {
			Statement stmt = con.createStatement();
			String SQL = "SELECT * FROM userlogin WHERE username='" + username + "' AND password='" + password + "'";
			
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.next()) { //succesful find
				System.out.println("Sucessful login");
				//String user = rs.getString("username");
				//String userpass = rs.getString("password");
				int id = rs.getInt("id");
				
				//System.out.println("The id is " + id);
				this.id = id;
				
				Subscriber sessionLogin = new Subscriber(username, password, id);
				return sessionLogin;
			} else return null;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return null;
		
		
	}

	@Override 
	public boolean Login(String username, String password) {
		this.username = username;
		this.password = password;
		Connection con = conn.connectDB();
		try {
			Statement stmt = con.createStatement();
			String SQL = "SELECT * FROM userlogin WHERE username='" + username + "' AND password='" + password + "'";
			
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.next()) { //succesful find
				System.out.println("Sucessful login");
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
				String IdString = rs.getString("id");
				int userId = Integer.parseInt(IdString);
				this.id = userId;
				
			} else return false;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return true;
	}
	
	
}
