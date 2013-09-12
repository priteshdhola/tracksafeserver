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
	
	protected UserActivityDAOImpl() {
		super(UserActivity.class);
	}
	
	@Override
	public List<UserActivity> getUserActivitiesByUID(int id){
		
		Session session = getSessionFactory().getCurrentSession();
		return session.createQuery(GET_USERACTIVITY_BY_USER).setInteger(0, id).list();
		
	}
	

}
