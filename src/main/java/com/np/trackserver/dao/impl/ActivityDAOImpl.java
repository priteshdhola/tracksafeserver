package com.np.trackserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.np.trackserver.dao.ActivityDAO;
import com.np.trackserver.dao.model.Activity;

/**
 * Activity DAO Implementation
 * @author npatel
 *
 */
@Repository
public class ActivityDAOImpl extends AbstractDAOImpl<Activity> implements ActivityDAO {

	protected ActivityDAOImpl() {
		super(Activity.class);
	}
	
	

	
}
