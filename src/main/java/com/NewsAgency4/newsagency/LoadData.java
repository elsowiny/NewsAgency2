package com.NewsAgency4.newsagency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
	
	public void LoadStories(int i) {
		
	}
	
	public List<NewsAgency> LoadSubscriptions(int subscriberId) {
		List<Integer> agenciesIds = new ArrayList<Integer>();
	    List<NewsAgency> agencies = new ArrayList<>(); 
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM subs WHERE subscriberId = '" + subscriberId +"'");
      while(res.next()){
          System.out.println(res.getString(1));
          System.out.println(res.getString(2));
    	  System.out.println("sub to is " + res.getInt("agencySubscribedToId"));
    	  int x = res.getInt("agencySubscribedToId");
    	  System.out.println("x is + " + x );
    	  agenciesIds.add(x);
    	  
          
      }
      for(int i=0;i<agenciesIds.size();i++) {
    	  System.out.println("List : " + agenciesIds.toString());
    	  System.out.println("i is in angenciesId.size()" + agenciesIds.get(i));
    	  int agencyID = agenciesIds.get(i);
    	  NewsAgency agency = (NewsAgency) findAgency(agencyID);
    	  agencies.add(agency);
    	  System.out.println(agency.agencyName);
      }
      
      /*
      
      for(int i=0;i< agenciesId.size(); i++) {
    	  System.out.println("i is in angenciesId.size()" + i);
    	  NewsAgency agency = (NewsAgency) findAgency(i);
    	  agencies.add(agency);
    	  System.out.println(agency.agencyName);
      }
      */
      return agencies;

        
        }catch(SQLException s){
        	return null;
        }
        
		
	}

	public void LoadNotifications(int i) {
		
	}
	
	
	public NewsAgency findAgency(int agencyId) {
		String name;
		String pass;
		int id = agencyId;
		
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM agencies WHERE agencyId = '" + agencyId +"'");
      while(res.next()){
    	  //since each id is unique
    	  System.out.println(res.getString("agencyName"));

    	  name = res.getString("agencyName");
    	  pass= res.getString("agencypassword");

    	  System.out.println(res.getString("agencyName"));
    	  NewsAgency tempagency = new NewsAgency(name, pass, id);
    	  return tempagency;
    	  
          
      }
	 
        
        }catch(SQLException s){
        	return null;
        }
		return null;
	}
	
	public void LoadAgencies() {
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT *FROM AGENCIES");
      while(res.next()){
          System.out.println(res.getString(1));
          System.out.println(res.getString(2));
          
      }
        
        }catch(SQLException s){
        	//return null;
        }
		
	}
}
