package com.np.trackserver.services.monitors;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.UserActivityDAO;
import com.np.trackserver.dao.model.Activity;
import com.np.trackserver.dao.model.UserActivity;
import com.np.trackserver.services.beans.Status;

@Component
public class ActivityStatusMonitor {
	
	@Autowired
	ActivityDAO activityDAO;
	
	@Autowired
	UserActivityDAO userActivityDAO;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//every 2 hours
	@Scheduled(fixedRate = 7200000)
	@Transactional
	public void run(){
		
		logger.info("Running Activity Status Monitor..");
		DateTime curDateTime = new DateTime();
		
		try {
			
			List<UserActivity> dbUserActivities = userActivityDAO.getAllActiveUserActivities();
			
			if(CollectionUtils.isEmpty(dbUserActivities)){
				return;
			} 
			
			//TODO: Keys should be immutable
			Map<Activity, Boolean> activitiesStatus = new HashMap<Activity, Boolean>();
			Map<UserActivity, Boolean> userActivitiesStatus = new HashMap<UserActivity, Boolean>();
			
			
			for(UserActivity dbUserActivity : dbUserActivities) {
				
				Activity dbActivity = dbUserActivity.getActivity();
				
				//Check start date; if it's past one day change both activity and useractivity status to STOP 
				Date date = dbActivity.getStartDate();
				DateTime startDate = date==null ? null : new DateTime(date);
				
				if(startDate == null){
					continue;
				}
				if(startDate.isBefore(curDateTime.getMillis())){
					
					if(activitiesStatus.get(dbActivity) == null){
						activitiesStatus.put(dbActivity, false);
					}
					if(userActivitiesStatus.get(dbUserActivity) == null){
						userActivitiesStatus.put(dbUserActivity, false);
					}
				}
				
				if(Status.STOP.getValue().equals(dbUserActivity.getStatus())){
					
					if(activitiesStatus.get(dbActivity) == null){
						activitiesStatus.put(dbActivity, false);
					}
					
				} else {
					
					if(activitiesStatus.get(dbActivity) == null){
						activitiesStatus.put(dbActivity, true);
					}
				}
			}
			// Update activity status to STOP if false
			for(Map.Entry<Activity, Boolean> entry : activitiesStatus.entrySet()){
				Activity activity = entry.getKey();
				Boolean status = entry.getValue();
				
				if(!status){
					activity.setStatus(Status.STOP.getValue());
					activityDAO.update(activity);
				}
			}
			
			// Update user activity status to STOP if false
			for(Map.Entry<UserActivity, Boolean> entry : userActivitiesStatus.entrySet()){
				UserActivity userActivity = entry.getKey();
				Boolean status = entry.getValue();
				
				if(!status){
					userActivity.setStatus(Status.STOP.getValue());
					userActivityDAO.update(userActivity);
				}
			}
			
		} catch (Exception e) {
			logger.error("Error running Activity Status Monitor.", ExceptionUtils.getStackTrace(e));
			throw e;
		}
		
		logger.info("Finishing Activity Status Monitor..");
	}

}
