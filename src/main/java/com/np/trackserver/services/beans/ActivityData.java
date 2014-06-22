package com.np.trackserver.services.beans;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.np.trackserver.services.beans.UserActivityData.Status;

/**
 * Activity model which maps to database Activity object
 * @author npatel
 *
 */
@XmlRootElement(name="activityData")
public class ActivityData {

		private Integer id;
		
		private String name;
		
		private Date startDate;
		
		private Integer type;

		private Date createdDate;
		
		private Date modifiedDate;
		
		private Integer createdBy;
		
		private List<UserActivityData> userActivities;
		
		private Status status;
		
		public Status getStatus() {
			return status;
		}
		public void setStatus(Status status) {
			this.status = status;
		}
		
		public List<UserActivityData> getUserActivities() {
			return userActivities;
		}
		public void setUserActivities(List<UserActivityData> userActivities) {
			this.userActivities = userActivities;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getModifiedDate() {
			return modifiedDate;
		}
		public void setModifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
		}
		public Integer getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Integer createdBy) {
			this.createdBy = createdBy;
		}
		
		@XmlType(namespace="activity", name = "status")
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
