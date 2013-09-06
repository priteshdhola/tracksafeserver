package com.np.trackserver.services.beans;

import java.util.Date;
import java.util.List;

/**
 * Activity model which maps to database Activity object
 * @author npatel
 *
 */
public class Activity {

		private int id;
		private String name;
		private Date startDate;
		private short type;
		private Date createdDate;
		private Date modifiedDate;
		
		private List<User> users;
		
		public Activity(){
			
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
		public short getType() {
			return type;
		}
		public void setType(short type) {
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
		public List<User> getUsers() {
			return users;
		}
		public void setUsers(List<User> users) {
			this.users = users;
		}
		
}
