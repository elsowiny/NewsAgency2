package com.NewsAgency4.newsagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class SubscriptionHandling {

	
	public static void subscribe(int agencyId, int subId, HashMap<Integer, String> availableAgencies) {
		if(!availableAgencies.containsKey(agencyId) || agencyId == 0) {
			return;
		}else {
			DBConnection conn = new DBConnection();
	        Connection con = conn.connectDB();
	        String sql = "INSERT INTO subs (subscriberId, agencySubscribedToId)" +
	        "VALUES(?, ?)";
	        
	       
	        
	        try{
	        	PreparedStatement preparedStatement = con.prepareStatement(sql);
	        	preparedStatement.setInt(1, subId);
	        	preparedStatement.setInt(2, agencyId);
	        	preparedStatement.executeUpdate(); 
	        	System.out.println("Subscribed to agency with ID " + agencyId);
	      
	      }catch(SQLException s){
	      //	return null;
	      }
	        
	        
	        
	        
			
		}
		
	}
	
	public static void unSubscribe(int agencyToUnsub, int userId, List<NewsAgency> usersAgencies) {
		List<Integer> currentAgencyIds = new ArrayList<>();
		
		for(NewsAgency agency: usersAgencies) {
        	currentAgencyIds.add(agency.getId());
        }
		if(!currentAgencyIds.contains(agencyToUnsub) || agencyToUnsub == 0) {
			return;
			
		}else {
			DBConnection conn = new DBConnection();
	        Connection con = conn.connectDB();
	        String sql = "DELETE FROM subs WHERE subscriberId = ? AND agencySubscribedToId = ?";
	        
	       
	        
	        try{
	        	PreparedStatement preparedStatement = con.prepareStatement(sql);
	        	preparedStatement.setInt(1, userId);
	        	preparedStatement.setInt(2, agencyToUnsub);
	        	preparedStatement.executeUpdate(); 
	        	System.out.println("Unsubscribed to agency with ID " + agencyToUnsub);
	      
	      }catch(SQLException s){
	      //	return null;
	      }
			
			
			
			
		}
		
		
	}
	
	
	public static HashMap<Integer, String> LoadAvilableAgencies(int subId) {
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        HashMap<Integer, String> allAgencyIdsAndName = new HashMap<>();
        List<NewsAgency> usersAgencies = LoadData.LoadSubscriptions(subId);
        List<Integer> currentAgencyIds = new ArrayList<>();
        HashMap<Integer, String> agenciesNotSubbedTo = new HashMap<>();
        List<Integer> allAgencyIds = new ArrayList<>();
        //list of current subscriptions
        for(NewsAgency agency: usersAgencies) {
        	currentAgencyIds.add(agency.getId());
        }
        
        
        String sql = "SELECT * FROM agencies";
        // String sql = "SELECT * FROM subs WHERE subscriberID = ?";
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery(sql);
      while(res.next()){
          int agencyId = res.getInt("agencyId");
          String agencyName = res.getString("agencyName");
          
          allAgencyIdsAndName.put(agencyId, agencyName);
          
      }
      //for each agency, check if we are subbed or not if we are
      //for(Entry<Integer, List<Story>> entry : storiesMap.entrySet())
      for(Entry<Integer, String> entry : allAgencyIdsAndName.entrySet()) {
    	  int agencyId = entry.getKey();
    	  String name = entry.getValue();
    	  if(currentAgencyIds.contains(agencyId)) {
    		  //ignore we are already subbed
    	  }else {
    		  agenciesNotSubbedTo.put(agencyId, name);
    	  }
    	  
    	  
    	  
      }
      
      return agenciesNotSubbedTo;
      
        //return agencyIdsAndName;
        }catch(SQLException s){
        	return null;
        }
		
	}
	
	
	

	
	
	
	
	
	
	
}
