package com.np.trackserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.np.trackserver.dao.UserRelationsDAO;
import com.np.trackserver.dao.model.UserRelations;

@Repository
public class UserRelationsDAOImpl extends AbstractDAOImpl<UserRelations> implements UserRelationsDAO {

	protected UserRelationsDAOImpl() {
		super(UserRelations.class);
	}
	
}
