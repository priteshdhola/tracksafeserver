package com.np.trackserver.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Abstract DAO
 * @author npatel
 *
 */

@Repository
public interface AbstractDAO<T> {
	
	public abstract T save(T object);

	public abstract T update(T object);
	
	public abstract T saveOrUpdate(T object);
	
	public abstract void delete(T object);
	
	public abstract void refresh(T object);
	
	public abstract T get(Serializable id);
	
	public abstract List<T> getAll();
	
	public boolean exists(String id);
	
	public void saveOrUpdate(List<T> object);

}
