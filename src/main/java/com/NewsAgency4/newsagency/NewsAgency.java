
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author elsow
 */



public class NewsAgency implements Subject {
    

    public int agencyId;
      
    //each agency has a name
    

	public String agencyName;
    

	public String agencypassword;
    
	
	//list of stories
	
    protected ArrayList<Story> stories = new ArrayList<Story>();
    
    //list of subscribers
    protected List<Observer> subscribers = new ArrayList<Observer>();

    public NewsAgency(String name, String password, int id){
        agencyName = name;
        agencypassword = password;
        agencyId = id;
    }
   
    
    public void addStory(Story story) {
    	this.stories.add(story);
    	
    }
    public void subscribe(Observer observer) {
    	this.attatch(observer);
    }
    
    public String getName(){
            return agencyName;
    }
    
    @Override
    public void removeObserver(Observer observer){
        subscribers.remove(observer);
    }
    
    
    @Override
    public List<Observer> getObservers(){
        return this.subscribers;
    }
    
    
    @Override 
    public void notifyAllObservers(){
        for(Observer observer: subscribers){
            observer.update(this);
        }
    }
    
    //for db use
    
    public void notifyAllObservers(List<Subscriber> subscribers){
    	
        for(Subscriber sub: subscribers){
            sub.update(this);
        }
    }
    
    
    
    @Override
    public void attatch(Observer observer){
        subscribers.add(observer);
    }
    public int getId() {
    	return agencyId;
    }
    
}