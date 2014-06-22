package com.np.trackserver.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.UserActivityDAO;
import com.np.trackserver.dao.model.Activity;
import com.np.trackserver.dao.model.User;
import com.np.trackserver.dao.model.UserActivity;
import com.np.trackserver.exceptions.NoResourceFoundException;
import com.np.trackserver.services.beans.ActivityData;
import com.np.trackserver.services.beans.LocationData;
import com.np.trackserver.services.beans.UserActivityData;
import com.np.trackserver.services.beans.UserData;

@Service

public class ActivityService {

	@Autowired
	ActivityDAO activityDAO;
	
	@Autowired
	UserActivityDAO userActivityDAO;
	
	private ConcurrentMap<Integer, ConcurrentMap<Integer, LocationData>> userActivityLocations = new ConcurrentHashMap<Integer, ConcurrentMap<Integer, LocationData>>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Transactional()
	public Integer createActivity(ActivityData activity, UserData user){
		
		Date cur = new Date();
		Activity dbActivity = new Activity();
		dbActivity.setName(activity.getName());
		dbActivity.setType(0);
		dbActivity.setStartDate(activity.getStartDate());
		dbActivity.setCreatedDate(cur);
		dbActivity.setCreatedBy(activity.getCreatedBy());
		dbActivity = activityDAO.save(dbActivity);
		
		User dbUser = new User();
		dbUser.setId(user.getId());

		UserActivity ua = new UserActivity();
		ua.setActivity(dbActivity);
		ua.setCreatedDate(cur);
		ua.setModifiedDate(cur);
		ua.setUser(dbUser);
		userActivityDAO.save(ua);
		
		return dbActivity.getId();
		
	}
	
	@Transactional(readOnly=true)
	public ActivityData getActivity(Integer id, Integer uid){
		
		UserActivity dbUActivity = userActivityDAO.getUserActivityByUIDAID(uid, id);
		
		if(dbUActivity == null) 
			throw new NoResourceFoundException("No Such Activity Found for loggedin user");
		
		return createActivityDataFromDBActivity(dbUActivity.getActivity());
	}
	
	@Transactional(readOnly=true)
	public List<ActivityData> getActivitiesByUserId(Integer id){
		
		List<UserActivity> dbUserActivities = userActivityDAO.getUserActivitiesByUID(id);
		
		Map<Activity, ActivityData> activityDataMap = null;
		
		if(CollectionUtils.isEmpty(dbUserActivities)){
			return null;
		} else {
			activityDataMap = new HashMap<Activity, ActivityData>();
		}
		for(UserActivity userActivity : dbUserActivities) {
			
			Activity activity = userActivity.getActivity();
			ActivityData activityData = null;
			
			if((activityData = activityDataMap.get(activity)) == null){
				
				activityData = createActivityDataFromDBActivity(activity);
				
				List<UserActivityData> userActivityDataList = new ArrayList<UserActivityData>();
				userActivityDataList.add(createUserActivityDataFromDBEntity(userActivity));
				
				activityData.setUserActivities(userActivityDataList);
				activityDataMap.put(activity, activityData);
				
			} else {
				
				List<UserActivityData> userActivityDataList =  activityData.getUserActivities();
				userActivityDataList.add(createUserActivityDataFromDBEntity(userActivity));
			}
		}
		return new ArrayList<ActivityData>(activityDataMap.values());
	}
	
	private UserActivityData createUserActivityDataFromDBEntity(UserActivity dbUserActivity){
		
		UserActivityData ua = new UserActivityData();
		UserData user = new UserData();
		user.setId(dbUserActivity.getUser().getId());
		user.setUserName(dbUserActivity.getUser().getUserName());
		
		ua.setUser(user);
		ua.setDistance(dbUserActivity.getDistance());
		ua.setPace(dbUserActivity.getPace());
		ua.setTime(dbUserActivity.getTime());
		ua.setStatus(UserActivityData.Status.fromValue(dbUserActivity.getStatus()));
		
		return ua;
	}
	
	private ActivityData createActivityDataFromDBActivity(Activity dbActivity){
		
		ActivityData activityData = new ActivityData();
        activityData.setId(dbActivity.getId());
		activityData.setName(dbActivity.getName());
		activityData.setType(dbActivity.getType());
		activityData.setStartDate(dbActivity.getStartDate());
		activityData.setCreatedDate(dbActivity.getCreatedDate());
		activityData.setCreatedBy(dbActivity.getCreatedBy());
		activityData.setStatus(ActivityData.Status.fromValue(dbActivity.getStatus()));

        /*
		if(dbUActivity != null){
			activityData.setTime(dbUActivity.getTime());
            activityData.setDistance(dbUActivity.getDistance());
			activityData.setPace(dbUActivity.getPace());
		} */
		
		return activityData;
	}
	
	
	public void trackUserLocationForActivity(Integer activityId, LocationData location){
		
		ConcurrentMap<Integer, LocationData> userLocations = this.userActivityLocations.get(activityId);
		if(userLocations == null){
			userLocations = new ConcurrentHashMap<Integer, LocationData>();
			userActivityLocations.put(activityId, userLocations);
		}
		userLocations.put(location.getUser().getId(), location);
	}
	
	public List<LocationData> getUsersLocationForActivity(Integer activityId){
		
		ConcurrentMap<Integer, LocationData> userLocations = this.userActivityLocations.get(activityId);
		if(userLocations == null) return null;
		
		Collection<LocationData> locations = userLocations.values();
		if(locations == null) return null;
		List<LocationData> list =  new ArrayList<LocationData>(locations);
		return list;
		
	}

	@Transactional
	public void updateActivityStatus(UserActivityData uaData, Integer activityId, Integer userId) {

		UserActivity ua = userActivityDAO.getUserActivityByUIDAID(userId, activityId);
		if(ua == null){
			throw new NoResourceFoundException("No Such Activity Found for loggedin user");
		}
		
		ua.setPace(uaData.getPace());
		ua.setTime(uaData.getTime());
		ua.setDistance(uaData.getDistance());
		ua.setStatus(uaData.getStatus().getValue());
		
		userActivityDAO.update(ua);
	}
}
