package com.NewsAgency4.newsagency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Notification {


	int id;
	
	
	String notification;
	
	int subscriberId;
	
	int read = 0;
	
	public Notification(String nameOfAgency, int subscriberId) {
		notification = nameOfAgency + " Just released a new article";
		this.subscriberId = subscriberId;
		
	}
	
	public String getNotification() {
		return notification;
	}
	public int getSubId() {
		return this.subscriberId;
	}
}
