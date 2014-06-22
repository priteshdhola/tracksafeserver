package com.np.trackserver.dao;

import java.util.List;

import com.np.trackserver.dao.model.UserActivity;

public interface UserActivityDAO extends AbstractDAO<UserActivity> {
	
	List<UserActivity> getUserActivitiesByUID(int id);
	
	UserActivity getUserActivityByUIDAID(int uid, int aid);
	
	List<UserActivity> getAllUserActivities();
	
	/**
	 * If Activity is still active all UserActivity considered active as well
	 * @return
	 */
	List<UserActivity> getAllActiveUserActivities();
	
}
