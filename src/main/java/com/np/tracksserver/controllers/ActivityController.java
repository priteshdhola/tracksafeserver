package com.np.tracksserver.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.services.beans.Activity;
import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;

public class ActivityController {
	
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	ActivityDAO activityDAO;
	
	public void createEvent(Request req, Response res) {
		
		Activity activity = req.getBodyAs(Activity.class);
		
		
	}
	public void getEvent(Request req, Response res) {
			
	}
	public void getEvents(Request req, Response res) {
		
	}
	public void updateEvent(Request req, Response res) {
		
	}
}
