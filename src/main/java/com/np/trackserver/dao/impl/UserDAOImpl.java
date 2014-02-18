package com.np.trackserver.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.np.trackserver.dao.UserDAO;
import com.np.trackserver.dao.model.User;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

	private static final String GET_BY_USERNAME = "from User u where u.email = ? ";
	
	protected UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User getByUserName(String email) {
		
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery(GET_BY_USERNAME);
		q.setString(0, email);
		List<User> users = q.list();
		if(!CollectionUtils.isEmpty(users)){
			return users.get(0);
		}
		return null;
	}

	
	
}
