package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * User activity bean which maps to useractivity data model
 * @author npatel
 *
 */
@XmlRootElement(name="userActivityData")
public class UserActivityData {
	
	private UserData user;
	private Integer activityId;
	private Long time;
	private Double distance;
	private Double pace;
	private Status status;
	
	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getPace() {
		return pace;
	}

	public void setPace(Double pace) {
		this.pace = pace;
	}

}
