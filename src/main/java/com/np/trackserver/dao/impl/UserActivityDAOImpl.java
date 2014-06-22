package com.np.trackserver.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.np.trackserver.dao.UserActivityDAO;
import com.np.trackserver.dao.model.UserActivity;

@Repository
public class UserActivityDAOImpl extends AbstractDAOImpl<UserActivity> implements
		UserActivityDAO {

	private static final String GET_USERACTIVITY_BY_USER = "from UserActivity ua where ua.user.id = ?";
	
	private static final String GET_ALL_USERACTIVITY = "from UserActivity ua";
	
	//2 is status "stop"
	private static final String GET_ALL_ACTIVE_USERACTIVITY = "from UserActivity ua where ua.activity.status != 2";
	
	private static final String GET_USERACTIVITY_BY_USER_ACTIVITY = "from UserActivity ua where ua.user.id = ? and ua.activity.id = ?";
	
	protected UserActivityDAOImpl() {
		super(UserActivity.class);
	}
	
	@Override
	public List<UserActivity> getUserActivitiesByUID(int id){
		
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery(GET_USERACTIVITY_BY_USER).setInteger(0, id).list();
		
	}

	@Override
	public UserActivity getUserActivityByUIDAID(int uid, int aid) {
		Session session = getSessionFactory().getCurrentSession();
		List<UserActivity> uas =  session.createQuery(GET_USERACTIVITY_BY_USER_ACTIVITY)
				.setInteger(0, uid)
				.setInteger(1, aid).list();
		if(uas != null && uas.size() > 0){
			return uas.get(0);
		}
		return null;
	}

	@Override
	public List<UserActivity> getAllUserActivities() {
		
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery(GET_ALL_USERACTIVITY).list();
	}

	@Override
	public List<UserActivity> getAllActiveUserActivities() {
		
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery(GET_ALL_ACTIVE_USERACTIVITY).list();
	}
	

}
