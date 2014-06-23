package com.np.trackserver.services.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="userActivityLocationDataList")
public class UserActivityLocationDataList {
	
	
	private List<UserActivityLocationData> userActivityLocationDataList;

	@XmlElement(name="userActivityLocationData")
	public List<UserActivityLocationData> getUserActivityLocationDataList() {
		return userActivityLocationDataList;
	}

	public void setUserActivityLocationDataList(
			List<UserActivityLocationData> userActivityLocationDataList) {
		this.userActivityLocationDataList = userActivityLocationDataList;
	}


}
