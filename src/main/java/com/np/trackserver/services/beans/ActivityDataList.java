package com.np.trackserver.services.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="activityDataList")
public class ActivityDataList {

	private List<ActivityData> activityList;
	
	public ActivityDataList() {}

	@XmlElement(name="activityData")
	public List<ActivityData> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityData> activityList) {
		this.activityList = activityList;
	}

}
