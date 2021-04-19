package com.NewsAgency4.newsagency.iterator;

import java.util.List;

public class NotificationRepo implements Container {

	List<String> notifications;
	
	
	@Override
	public Iterator getIterator() {
		
		return new NotificationIterator(notifications);
	}

	public NotificationRepo(List<String> notifications) {
		this.notifications = notifications;
	}
	

}
