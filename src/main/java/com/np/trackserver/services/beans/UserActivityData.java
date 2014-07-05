package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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

	@XmlType(namespace="useractivity", name = "status")
	@XmlEnum
	public enum Status{
		
		NOT_STARTED(0), RUNNING(1), STOP(2);
		
		private Integer val;
		
		Status(int val){
			this.val = val;
		}
		
		public Integer getValue(){
			return val;
		}
		
		public static Status fromValue(Integer val){
			
			for(Status t : Status.values()){
				if(t.equals(val)){
					return t;
				}
			}
			if(null == val){
				return Status.NOT_STARTED;
			}
			throw new IllegalArgumentException(""+val);
		}
		
	}

	
}
