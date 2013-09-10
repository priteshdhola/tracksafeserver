package com.np.trackserver.services.beans;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Activity model which maps to database Activity object
 * @author npatel
 *
 */
@XmlRootElement(name="activityData")
public class ActivityData {

		private int id;
		
		private String name;
		
		private Date startDate;
		
		private Integer type;

		private Date createdDate;
		
		private Date modifiedDate;
		
		private List<UserData> users;
		
		public ActivityData(){
			
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
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
		public List<UserData> getUsers() {
			return users;
		}
		public void setUsers(List<UserData> users) {
			this.users = users;
		}
		
}
