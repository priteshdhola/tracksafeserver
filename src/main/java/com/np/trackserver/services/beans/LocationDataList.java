package com.np.trackserver.services.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name="locationDataList")
public class LocationDataList {
	
	private List<LocationData> locationList;

	@XmlElement("locationData")
	public List<LocationData> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<LocationData> locationList) {
		this.locationList = locationList;
	}
	
	

}
