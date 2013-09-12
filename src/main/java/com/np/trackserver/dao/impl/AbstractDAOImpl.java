package com.np.trackserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.np.trackserver.dao.AbstractDAO;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

	private static final Logger logger = Logger.getLogger(AbstractDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> entityClass;
	
	protected AbstractDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public T get(Serializable id) {

		Session session = sessionFactory.getCurrentSession();
		T t = (T) session.get(entityClass, id);
		//session.close();
		return t;

	}

	/*
	 * protected final Session getCurrentSession() {
	 * 
	 * return this.sessionFactory.getCurrentSession(); }
	 */

	@Override
	public T save(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.save(object);
		//session.close();
		return object;
	}

	@Override
	public T update(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.update(object);
		//session.flush();
		//session.close();
		return object;	}

	@Override
	public T saveOrUpdate(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(object);
		//session.flush();
		//session.close();
		return object;	
	}

	@Override
	public void delete(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(object);
		//session.flush();
		//session.close();
	}

	@Override
	public void refresh(T object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> getAll() {
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(entityClass).list();
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveOrUpdate(List<T> object) {
		// TODO Auto-generated method stub

	}
	

}
