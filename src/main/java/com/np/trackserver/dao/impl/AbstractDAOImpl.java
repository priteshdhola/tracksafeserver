package com.np.trackserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.np.trackserver.dao.AbstractDAO;

public abstract class AbstractDAOImpl implements AbstractDAO {

	private static final Logger logger = Logger.getLogger(AbstractDAOImpl.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void update(Object object) {
		
		try {
			
		} catch (Exception e) {
			
		} finally {
			
		}
		
	}

	@Override
	public Object get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
