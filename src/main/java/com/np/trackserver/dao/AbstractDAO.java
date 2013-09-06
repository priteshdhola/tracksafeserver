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
	
	public abstract void update(T object);
	
	public abstract T get(Serializable id);

	public abstract List<T> getAll();
	

}
