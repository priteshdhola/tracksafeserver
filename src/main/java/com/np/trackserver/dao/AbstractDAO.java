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
	
	/**
	 * 
	 * @param object
	 * @return T
	 */

	public abstract T save(T object);

	/**
	 * 
	 * @param object
	 * @return T
	 */

	public abstract T update(T object);

	/**
	 * 
	 * @param object
	 * @return T
	 */

	public abstract T saveOrUpdate(T object);

	/**
	 * 
	 * @param object
	 */

	public abstract void delete(T object);



	public abstract void refresh(T object);


	/**
	 * 
	 * @param id
	 * @return T
	 */

	public abstract T get(Serializable id);

	/**
	 * 
	 * @return T
	 */

	public abstract List<T> getAll();

	/**
	 * 
	 * @param id
	 * @return T
	 */
	public boolean exists(String id);

	/**
	 * 
	 * @param object
	 */

	public void saveOrUpdate(List<T> object);

	

}
