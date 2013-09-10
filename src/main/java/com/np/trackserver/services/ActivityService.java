package com.np.trackserver.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.model.Activity;
import com.np.trackserver.services.beans.ActivityData;

@Service
@Transactional
public class ActivityService {

	@Autowired
	ActivityDAO activityDAO;
	
	public Integer createActivity(ActivityData activity){
		
		Activity dbActivity = new Activity();
		dbActivity.setName(activity.getName());
		dbActivity.setType(0);
		dbActivity.setStartDate(activity.getStartDate());
		dbActivity.setCreatedDate(new Date());
		
		activityDAO.save(dbActivity);
		
		return dbActivity.getId();
		
	}
	
	public ActivityData getActivity(Integer id){
		
		Activity dbActivity = activityDAO.get(id);
		
		ActivityData activityData = new ActivityData();
		activityData.setName(dbActivity.getName());
		activityData.setType(dbActivity.getType());
		activityData.setStartDate(dbActivity.getStartDate());
		activityData.setCreatedDate(dbActivity.getCreatedDate());
		
		return activityData;
	}
	
}
