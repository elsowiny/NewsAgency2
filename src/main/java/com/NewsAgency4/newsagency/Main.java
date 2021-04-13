/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    	    List<NewsAgency> usersAgency = userData.LoadSubscriptions(id);
    		for(NewsAgency agency :usersAgency) {
    			String agencyN = agency.getName();
    			System.out.println(agencyN);
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
 