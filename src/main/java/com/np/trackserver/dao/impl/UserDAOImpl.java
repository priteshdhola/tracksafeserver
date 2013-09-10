package com.np.trackserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.np.trackserver.dao.UserDAO;
import com.np.trackserver.dao.model.User;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

	protected UserDAOImpl() {
		super(User.class);
	}

	
	
}
