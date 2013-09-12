package com.np.trackserver.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.UserActivityDAO;
import com.np.trackserver.dao.model.Activity;
import com.np.trackserver.dao.model.UserActivity;
import com.np.trackserver.services.beans.ActivityData;

@Service

public class ActivityService {

	@Autowired
	ActivityDAO activityDAO;
	
	@Autowired
	UserActivityDAO userActivityDAO;
	
	@Transactional()
	public Integer createActivity(ActivityData activity){
		
		Activity dbActivity = new Activity();
		dbActivity.setName(activity.getName());
		dbActivity.setType(0);
		dbActivity.setStartDate(activity.getStartDate());
		dbActivity.setCreatedDate(new Date());
		dbActivity.setCreatedBy(activity.getCreatedBy());
		activityDAO.save(dbActivity);
		
		return dbActivity.getId();
		
	}
	
	@Transactional(readOnly=true)
	public ActivityData getActivity(Integer id){
		
		Activity dbActivity = activityDAO.get(id);
		
		if(dbActivity == null) return null;
		
		return createActivityDataFromDBActivity(dbActivity);
	}
	
	@Transactional(readOnly=true)
	public List<ActivityData> getActivitiesByUserId(Integer id){
		
		List<UserActivity> dbUserActivities = userActivityDAO.getUserActivitiesByUID(id);
		List<ActivityData> activities = null;
		
		if(CollectionUtils.isEmpty(dbUserActivities)){
			return null;
		} else {
			activities = new ArrayList<ActivityData>();
		}
		for(UserActivity ua : dbUserActivities) {
			
			Activity dbActivity = ua.getActivity();
			activities.add(createActivityDataFromDBActivity(dbActivity));
		}
		return activities;
	}
	
	private ActivityData createActivityDataFromDBActivity(Activity dbActivity){
		
		ActivityData activityData = new ActivityData();
		activityData.setName(dbActivity.getName());
		activityData.setType(dbActivity.getType());
		activityData.setStartDate(dbActivity.getStartDate());
		activityData.setCreatedDate(dbActivity.getCreatedDate());
		activityData.setCreatedBy(dbActivity.getCreatedBy());
		
		return activityData;
	}
	
	
}
