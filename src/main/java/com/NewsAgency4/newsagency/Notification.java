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
	
	int subsciberId;
	
	public Notification(String nameOfAgency) {
		notification = nameOfAgency + "Just released a new article";
		
	}
	
	public String getNotification() {
		return notification;
	}
}
