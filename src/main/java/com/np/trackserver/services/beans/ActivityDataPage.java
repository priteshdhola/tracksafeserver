package com.np.trackserver.services.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="page")
public class ActivityDataPage {

	private List<ActivityData> activityList;
	
	public ActivityDataPage() {}

	@XmlElement(name="activity")
	public List<ActivityData> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityData> activityList) {
		this.activityList = activityList;
	}

}
