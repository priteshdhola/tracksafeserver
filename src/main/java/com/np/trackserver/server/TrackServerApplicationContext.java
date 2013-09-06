package com.np.trackserver.server;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author npatel
 *
 */
public class TrackServerApplicationContext implements ApplicationContextAware {

	private static final Logger logger = Logger.getLogger(TrackServerApplicationContext.class);
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		 context = applicationContext;
	}
	
	public static <T> T getBean(String name, Class<T> requiredType){
		
		return context.getBean(name, requiredType);
		
	}
	
	public static <T> T getBean(Class<T> requiredType){
		
		return context.getBean(requiredType);
		
	}
}
