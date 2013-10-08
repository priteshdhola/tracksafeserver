package com.np.trackserver.services.messaging;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

//@Service
public class NotificationRegistry {
	
	private List<Notification> receivedNotifications = new ArrayList<Notification>();

	public List<Notification> getReceivedNotifications() {
		return receivedNotifications;
	}
	
	public void registerNotification(Notification notification) {
		receivedNotifications.add(notification);
	}
	
	public void clear() {
		receivedNotifications.clear();
	}

}
