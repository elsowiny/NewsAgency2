/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

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
    

    public void update(NewsAgency agency){
       
        
        String agencyName = agency.getName();
        Notification tempNotification = new Notification(agencyName);
        
      
        
      
        notifications.add(tempNotification);
        
    }
    
    public int getId() {
    	return id;
    }
    
}
