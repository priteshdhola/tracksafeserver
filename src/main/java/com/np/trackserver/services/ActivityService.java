package com.np.trackserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.services.beans.Activity;

@Service
public class ActivityService {

	@Autowired
	ActivityDAO activityDAO;
	
	public void saveActivity(Activity activity){
		
		//Do request validation
		
	}
	
}
