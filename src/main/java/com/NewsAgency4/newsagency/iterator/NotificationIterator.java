package com.NewsAgency4.newsagency.iterator;

import java.util.List;

public class NotificationIterator implements Iterator {

	public List<String> notifications;
	int pointer = 0;
	

	@Override
	public boolean hasNext() {
		return pointer != -1;
	}

	@Override
	public Object next() {
		Object obj = notifications.get(pointer);
		pointer = pointer - 1;
		return obj;
	}

	public NotificationIterator(List<String> notifications) {
		this.notifications = notifications;
	}
}
