package com.NewsAgency4.newsagency.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.NewsAgency4.newsagency.DBConnection;
import com.NewsAgency4.newsagency.NewsAgency;

public class AgencyLogin extends DBLogin{
	
	String username;
	String password;
	DBConnection conn = new DBConnection();
	int id;
	
	public NewsAgency createLoginSession(String username, String password) {
		
		//create an obj that we pull all data from sql table to recreate
		//rather than persisting the obj itself in hibernate, because the tables get too complex
		//with the FK, and joins and using hibernate
		Connection con = conn.connectDB();
		try {
			Statement stmt = con.createStatement();
			String SQL = "SELECT * FROM agencies WHERE agencyName='" + username + "' AND agencypassword='" + password + "'";
			
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.next()) { //succesful find
				String agencyName = rs.getString("agencyName");
				int agencyId = rs.getInt("agencyId");
				this.id = agencyId;
				NewsAgency sessionObj = new NewsAgency(agencyName, password, agencyId);
				return sessionObj;

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
			String SQL = "SELECT * FROM agencies WHERE agencyName='" + username + "' AND agencypassword='" + password + "'";
			
			ResultSet rs = stmt.executeQuery(SQL);
			if(rs.next()) { //succesful find
				System.out.println("Sucessful login");
				/*
				 * String agencyName = rs.getString("agencyName"); String agencyIdString =
				 * rs.getString("agencyId"); int agencyId = Integer.parseInt(agencyIdString);
				 * this.id = agencyId;
				 */
				

			} else return false;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return true;
	}

}
