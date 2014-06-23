package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author npatel
 *
 */
@XmlRootElement(name="userActivityLocationData")
public class UserActivityLocationData {
	
	private LocationData locationData;
	private UserActivityData userActivityData;
	
	public LocationData getLocationData() {
		return locationData;
	}
	public void setLocationData(LocationData locationData) {
		this.locationData = locationData;
	}
	public UserActivityData getUserActivityData() {
		return userActivityData;
	}
	public void setUserActivityData(UserActivityData userActivityData) {
		this.userActivityData = userActivityData;
	}
	
	

}
