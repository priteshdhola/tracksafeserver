package com.np.trackserver.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class SpringPropertyUtils extends PropertySourcesPlaceholderConfigurer {
	@SuppressWarnings("rawtypes")
	private static Map properties = new HashMap();

	@SuppressWarnings("unchecked")
	@Override
	protected void loadProperties(final Properties props) throws IOException {
		super.loadProperties(props);
		for (final Object key : props.keySet()) {
			properties.put((String) key, props.getProperty((String) key));
		}
	}

	/**
	 * Return a property loaded by the place holder.
	 * 
	 * @param name the property name.
	 * @return the property value.
	 */
	public static String getProperty(final String name) {
		return (String) properties.get(name);
	}
	
	/**
	 * Return a property loaded by the place holder.
	 * 
	 * @param name the property name.
	 * @return the property value.
	 */
	public static Integer getIntegerProperty(final String name) {
		
		String propValue = getProperty(name);
		return (propValue == null) ? null :Integer.valueOf(propValue.trim());
	}
	
	public static Long getLongProperty(final String name) {
		String propValue = getProperty(name);
		return (propValue == null) ? null : Long.valueOf(propValue.trim());
	}
	
	public static Boolean getBooleanProperty(String name) {
		String propValue = getProperty(name);
		return (propValue == null) ? false : BooleanUtils.toBoolean(new Integer(propValue.trim()));
	}
}