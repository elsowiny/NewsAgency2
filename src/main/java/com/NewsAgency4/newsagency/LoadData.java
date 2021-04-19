package com.NewsAgency4.newsagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadData {
	
	public List<Subscriber> LoadObservers(int agencyId) {
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        List<Integer> subscriberIds = new ArrayList<Integer>();
        List<Subscriber> observers = new ArrayList<Subscriber>();
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM subs WHERE agencySubscribedToId = '" + agencyId +"'");
      while(res.next()){

    	  int subscriberId = res.getInt("subscriberId");
    
    	  subscriberIds.add(subscriberId);
    	  
          
      }
      
      for(int i=0;i<subscriberIds.size(); i++) {
     	 int subscriberID = subscriberIds.get(i);
     	  
     	 Subscriber subInfo = findSubscriber(subscriberID);
     	 //NewsAgency agency = (NewsAgency) findAgency(agencyID);
     	  observers.add(subInfo);
     	  //System.out.println(agency.agencyName);
     	  
       }
      return observers;
	}catch(SQLException s){
    	return null;
    }
        
	}
	
	
	
	public static List<NewsAgency> LoadSubscriptions(int subscriberId) {
		List<Integer> agenciesIds = new ArrayList<Integer>();
	    List<NewsAgency> agencies = new ArrayList<>(); 
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM subs WHERE subscriberId = '" + subscriberId +"'");
      while(res.next()){
        //  System.out.println(res.getString(1));
        //  System.out.println(res.getString(2));
    	//  System.out.println("sub to is " + res.getInt("agencySubscribedToId"));
    	  int x = res.getInt("agencySubscribedToId");
    	//  System.out.println("x is + " + x );
    	  agenciesIds.add(x);
    	  
          
      }
      for(int i=0;i<agenciesIds.size();i++) {
    	 // System.out.println("List : " + agenciesIds.toString());
    	//  System.out.println("i is in angenciesId.size()" + agenciesIds.get(i));
    	  int agencyID = agenciesIds.get(i);
    	  NewsAgency agency = (NewsAgency) findAgency(agencyID);
    	  agencies.add(agency);
    	  //System.out.println(agency.agencyName);
      }
      
  
      return agencies;

        
        }catch(SQLException s){
        	return null;
        }
        
		
	}

	public List<String> LoadNotifications(int i) {
		//when this is called we should alter db for next time it is called
		DBConnection conn = new DBConnection();
		Connection con = conn.connectDB();
        String sql = "SELECT * FROM notification WHERE subscriberId = ? AND hasRead = ?";
 
        
        List<String> notifications = new ArrayList<String>();
       
        try {
        	PreparedStatement sqlStatement = con.prepareStatement(sql);
        
        	sqlStatement.setInt(1, i);
        	sqlStatement.setInt(2, 0);
        	//Statement st = con.createStatement();
        	ResultSet res = sqlStatement.executeQuery();
        	while(res.next()) {
        		 String notificationString = res.getString("notification");
        		 int read = res.getInt("hasRead");
        		// System.out.println(notificationString);
        		 System.out.println("read is " + read);
        		 if(read == 1) {
        			 //ignore
        		 }else {
        			 notifications.add(notificationString);        	
        		 }
        		
        		}

        	updateNotificationDB(i);
        	return notifications;
        }
        
        catch(SQLException s) {
        	return null;
        }
              
		
	}
	
	public HashMap<Integer, List<Story>> loadUserStories(int subId) {
		List<Integer> agencyIds = new ArrayList<Integer>();
		List<NewsAgency> agencies = new ArrayList<>(); 
		HashMap<Integer, List<Story>> storiesMap = new HashMap<Integer, List<Story>>();
		
		DBConnection conn = new DBConnection();
		Connection con = conn.connectDB();
		//need list of subsList
		List<NewsAgency> subscriptions = LoadSubscriptions(subId);
		for(NewsAgency newsAgency: subscriptions) {
			int newsAgencyId = newsAgency.getId();
			agencyIds.add(newsAgencyId);
		}
		//now we have a list of agency id's where we will load their stories
		
		for(int i=0;i<agencyIds.size();i++) {
			int agencyId = agencyIds.get(i);
			List<Story> agencysStories = loadAgencyStories(agencyId);
			storiesMap.put(agencyId, agencysStories);
		}
		return storiesMap;
	}
	
	
	public static List<Story> loadAgencyStories(int agencyId) {
		List<Story> stories = new ArrayList<>();
		DBConnection conn = new DBConnection();
		Connection con = conn.connectDB();
		String sql = "SELECT * FROM stories WHERE agencyId = ?";
		
		try {
        	PreparedStatement sqlStatement = con.prepareStatement(sql);
        	sqlStatement.setInt(1, agencyId);
        	ResultSet res = sqlStatement.executeQuery();
        	while(res.next()) {
       		 String headline = res.getString("headline");
       		 String story = res.getString("stories");
       		 String agencyreporting = res.getString("agencyreporting");
       		 // public Story(String headline, String stories, int agencyId, String agencyreporting)
       		Story agencyStory = new Story(headline, story, agencyId, agencyreporting);
       		stories.add(agencyStory);
       		}
        	return stories;
        	
		}catch(SQLException s) {
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void updateNotificationDB(int subId) {
		DBConnection conn = new DBConnection();
		Connection con = conn.connectDB();
		String sqlRead = "UPDATE `notification` SET hasRead =? WHERE subscriberId =?";
		 try {
			 
			PreparedStatement updateExp = con.prepareStatement(sqlRead);
			updateExp.setInt(1, 1);
			updateExp.setInt(2, subId);
			   int updateEXP_done = updateExp.executeUpdate();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addStoryToDb(Story story, int agencyId) {
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        String sql = "INSERT INTO stories (headline, stories, agencyid, agencyreporting)" +
                "VALUES (?, ?, ?, ?)";
        
        try{
        	PreparedStatement preparedStatement = con.prepareStatement(sql);
        	preparedStatement.setString(1, story.getHeadline());
        	preparedStatement.setString(2, story.getStories());
        	preparedStatement.setInt(3, story.getAgencyId());
        	preparedStatement.setString(4, story.getAgencyreporting());
        	preparedStatement.executeUpdate(); 
        	System.out.println("Story added");
      
      }catch(SQLException s){
      //	return null;
      }
		
		
	}
	
	
	public Subscriber findSubscriber(int subscriberId) {
		String name;
		String pass;
		
		
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        
        try{
      Statement st = con.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM userlogin WHERE id = '" + subscriberId +"'");
      while(res.next()){
    	  //since each id is unique
    	

    	  name = res.getString("username");
    	  pass= res.getString("password");

    	  Subscriber tempSub = new Subscriber(name, pass, subscriberId);
    	  return tempSub;
    	  
          
      }
	 
        
        }catch(SQLException s){
        	return null;
        }
		return null;
	}
	
	
	public static NewsAgency findAgency(int agencyId) {
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
    	//  System.out.println(res.getString("agencyName"));

    	  name = res.getString("agencyName");
    	  pass= res.getString("agencypassword");

    	//  System.out.println(res.getString("agencyName"));
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
