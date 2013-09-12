package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User tracking information belongs to event
 * @author npatel
 *
 */
@XmlRootElement(name="locationData")
public class LocationData {

	private UserData user;
	private Double latitude;
	private Double longitude;
	private Long timestamp;
	
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
