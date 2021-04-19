/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

import java.util.List;

/**
 *
 * @author elsow
 */
public interface Subject {

	public void attatch(Observer observer);
	  
	public void notifyAllObservers();
	    
	public List<Observer> getObservers();
	
	public void removeObserver(Observer observer);

}