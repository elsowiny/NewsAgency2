/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.NewsAgency4.newsagency.iterator.Iterator;
import com.NewsAgency4.newsagency.iterator.NotificationRepo;
import com.NewsAgency4.newsagency.login.AgencyLogin;
import com.NewsAgency4.newsagency.login.UserLogin;

/**
 *
 * @author elsow
 */
public class Main {
    
    public static void main(String[] args){
    
    System.out.println("User or agency?");
    Scanner sc = new Scanner(System.in);
    String answer = sc.nextLine();
    if(answer.equalsIgnoreCase("agency")) {
    	//ask for credentials
    	System.out.println("Username?");
    	String agencyUser = sc.nextLine();
    	System.out.println("password?");
    	String password = sc.nextLine();
    	
    	AgencyLogin agencySession = new AgencyLogin();
    	//create object?
    	if(agencySession.Login(agencyUser, password)) {
    		NewsAgency agencyLoginSession = agencySession.createLoginSession(agencyUser, password);
    		int id = agencyLoginSession.getId();
    		System.out.println(id);
    		System.out.println("Would you like to add a story?");
    		String addStory = sc.nextLine();
    		if(addStory.equalsIgnoreCase("yes")) {
    			//Ask for the info
    			System.out.println("headline?");
    			
    			String headline = sc.nextLine();
    			System.out.println("story?");
    			String story = sc.nextLine();
    			// public Story(String headline, String stories, int agencyId, String agencyreporting)
    			Story newStory = new Story(headline, story, id, agencyUser);
    			LoadData agencyData = new LoadData();
    			agencyData.addStoryToDb(newStory, id);
    			//story added
    			
    			List<Subscriber> subscribers = new ArrayList<Subscriber>();
    			LoadData agencyDataSession = new LoadData();
    			subscribers = agencyDataSession.LoadObservers(id);
    			
    			//notify observers
    			agencyLoginSession.notifyAllObservers(subscribers);
				/*
				 * for(Subscriber sub: subscribers) { //create notification add to db
				 * Notification newNotification = new Notification(agencyUser, sub.getId());
				 * sub.update(newNotification); //public Notification(String nameOfAgency, int
				 * subscriberId) { }
				 */
    			System.out.println("notifications added");
    			
    		}else {
    			
    			List<Subscriber> subscribers = new ArrayList<Subscriber>();
    			LoadData agencyData = new LoadData();
    			subscribers = agencyData.LoadObservers(id);
    			for(Subscriber sub: subscribers) {
    				String name = sub.getName();
    				System.out.println("Your subscribers are " + name);
    		}
			
				
			}
    		
    		
    		
    	};
    	
    	
    }else if(answer.equalsIgnoreCase("user")) {
    	//ask for credentials
    	System.out.println("Username?");
    	String userLogin = sc.nextLine();
    	System.out.println("password?");
    	String password = sc.nextLine();
    	
    	UserLogin userSession = new UserLogin();
    	//create object?
    	if(userSession.Login(userLogin, password)) {
    		Subscriber loginSession = userSession.createLoginSession(userLogin, password);
    		int id = loginSession.getId();
    		LoadData userData = new LoadData();
    		//List<NewsAgency> usersAgency;
    	    List<NewsAgency> usersAgency = LoadData.LoadSubscriptions(id);
    	    System.out.println("Your subscriptions, " + userLogin);
    		for(NewsAgency agency :usersAgency) {
    			String agencyN = agency.getName();
    			System.out.println(agencyN);
    		}
    		//load notifications
    		//load notification iterator
    		List<String> notifications = userData.LoadNotifications(id);
    		if( notifications == null || notifications.size() ==0 ) {
    			System.out.println("no new notifications");
    		}
    		else 
    		{
    		NotificationRepo notificationIterator = new NotificationRepo(notifications);
    		Iterator notificationIter = notificationIterator.getIterator();
    		while(notificationIter.hasNext()) {
    			System.out.println(notificationIter.next() + "");
    		}
    		}
    		System.out.println("Views stories or edit subscriptions?");
    		String editOrView = sc.nextLine();
    		if(editOrView.equalsIgnoreCase("view")) {
    			//view subs stories
    			HashMap<Integer, List<Story>> storiesMap = userData.loadUserStories(id);
    			//iterate over it
    			
    			for(Entry<Integer, List<Story>> entry : storiesMap.entrySet()) {
    				int key = entry.getKey();
    				List<Story> value = entry.getValue();
    				//print out the stories and the agency
    				System.out.println("Agency id of " + key + "'s stories are");
    				for(Story story: value) {
    					String headline = story.headline;
    					String article = story.stories;
    					String agencyNa = story.agencyreporting;
    					
    					System.out.println("Headline: " + headline);
    					//System.out.println(headline);
    					System.out.println();
    					System.out.println("story " +article);
    					System.out.println();
    					System.out.println("by " + agencyNa);
    					//System.out.println(agencyNa);
    					System.out.println();
    				}
    			}
    			
    		}
    			
    			else if(editOrView.equalsIgnoreCase("edit")){
    				//edit
    				//sub or unsub
    				System.out.println("Sub or Unsub?");
    				String subOrUnsub = sc.nextLine();
    				if(subOrUnsub.equalsIgnoreCase("sub")) {
    					
    					//give subscription option
    					System.out.println("Here are the available subscriptions:");
    					HashMap<Integer, String> availableSubscriptions = SubscriptionHandling.LoadAvilableAgencies(id);
    					for(Entry<Integer, String> entry : availableSubscriptions.entrySet()) {
    						
    						System.out.println("Agency Name: " + entry.getValue() + "Id: " + entry.getKey());
    					}
    					System.out.println("Enter the id of the agency you'd like to subscribe to, enter 0 to exit");
    					int agencyToSubscribe = sc.nextInt();
    					SubscriptionHandling.subscribe(agencyToSubscribe, id, availableSubscriptions);
    				}else if(subOrUnsub.equalsIgnoreCase("unsub")) {
    					//give unsub window
    					System.out.println("Enter the id of the agency you'd like to unSubscribe to, enter 0 to exit");
    					for(NewsAgency agency :usersAgency) {
    		    			String agencyN = agency.getName();
    		    			System.out.println(agencyN + "  Id of " + agency.getId());
    		    		}
    					int agencyToUnsub = sc.nextInt();
    					SubscriptionHandling.unSubscribe(agencyToUnsub, id, usersAgency);
    					
    				}
    				
    				
    			}
    		
    		
    		
    		//System.out.println(id);
    	};
    	
    }
    else {
    	System.out.println("error");
    }
    //System.out.println(answer);
    //UserLogin userSession = new UserLogin();
    //userSession.Login("bob", "password");
    
    
    }
    
}
 