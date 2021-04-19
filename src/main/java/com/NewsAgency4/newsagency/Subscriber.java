/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author elsow
 */

//each subscriber will observe a newsagency


public class Subscriber implements Observer{
    


	int id;
	


    String username;
	


    String password;
    
    
    
    public Subscriber(String name, String password, int id){
        username = name;
        this.password = password;
        this.id = id;
    }

    protected List<NewsAgency> agencies; 
	

    protected List<Notification> notifications;
    
    public Subscriber(String name, String password){
        username = name;
        this.password = password;
    }
    

    
    public int getId() {
    	return id;
    }
    
    public String getName() {
    	return username;
    }


	@Override
	public void update(NewsAgency newsAgency) {
		// TODO Auto-generated method stub
		//public Notification(String nameOfAgency, int subscriberId)
		DBConnection conn = new DBConnection();
        Connection con = conn.connectDB();
        System.out.println("Preparing to create notification");
        Notification newNotification = new Notification(newsAgency.getName(), this.getId());

        System.out.println("notification is " + newNotification.notification + "and agency " + newsAgency.getName()
        + 
        "subscriber id is + " + this.getId() );
        //String sql = "INSERT INTO `notification` (notification, subscriberId, 'read') VALUES(?,?,?)";
        
        try{
        	//PreparedStatement preparedStatement = con.prepareStatement(sql);
        	PreparedStatement preparedStatement = con.prepareStatement(
        			"INSERT INTO `notification` (notification, subscriberId, hasread) VALUES(?,?,?)"
        			);
        	preparedStatement.setString(1, newNotification.getNotification() + "");
        	preparedStatement.setInt(2, newNotification.getSubId());
        	preparedStatement.setInt(3, 0);
        	preparedStatement.executeUpdate(); 
        	System.out.println("Notification for subscriber id " + newNotification.getSubId() + "added") ;
      
      }catch(SQLException s){
      System.out.println(s);
      }
		
	}


	
    
}
