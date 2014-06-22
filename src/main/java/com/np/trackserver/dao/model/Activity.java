package com.np.trackserver.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Activity model which maps to database Activity object
 * @author npatel
 *
 */
@Entity
@Table(name="activity")
public class Activity {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@Column(name="name")
		private String name;
		
		@Column(name="start_date")
		private Date startDate;
		
		@Column(name="type")
		private Integer type;
		
		@Column(name="status")
		private Integer status;
		
		@Column(name="created_date")
		private Date createdDate;
		
		@Column(name="modified_date")
		private Date modifiedDate;
		
		@Column(name="created_by")
		private Integer createdBy;
		
		public Activity(){
			
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
		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
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

		@Override
		public int hashCode() {
			
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((createdBy == null) ? 0 : createdBy.hashCode());
			result = prime * result
					+ ((createdDate == null) ? 0 : createdDate.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((startDate == null) ? 0 : startDate.hashCode());
			result = prime * result
					+ ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Activity other = (Activity) obj;
			if (createdBy == null) {
				if (other.createdBy != null)
					return false;
			} else if (!createdBy.equals(other.createdBy))
				return false;
			if (createdDate == null) {
				if (other.createdDate != null)
					return false;
			} else if (!createdDate.equals(other.createdDate))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (modifiedDate == null) {
				if (other.modifiedDate != null)
					return false;
			} else if (!modifiedDate.equals(other.modifiedDate))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}
		
		
		
		
}
