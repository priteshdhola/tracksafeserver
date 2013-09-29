package com.np.trackserver.services.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="locationDataList")
public class LocationDataList {
	
	
	private List<LocationData> locationList;

	@XmlElement(name="locationData")
	public List<LocationData> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<LocationData> locationList) {
		this.locationList = locationList;
	}
	
	

}
