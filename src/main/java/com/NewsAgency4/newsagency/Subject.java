/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

/**
 *
 * @author elsow
 */
public interface Subject {

	public void attatch(Observer observer);
	  
	public void notifyAllObservers();
	    
	public String getObservers();
	
	public void removeObserver(Observer observer);

}