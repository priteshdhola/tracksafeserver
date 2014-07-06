package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "status")
@XmlEnum
public enum Status{
	
	@XmlEnumValue("0")NOT_STARTED(0), 
	@XmlEnumValue("1")RUNNING(1), 
	@XmlEnumValue("2")STOP(2);
	
	private Integer val;
	
	Status(int val){
		this.val = val;
	}
	
	public Integer getValue(){
		return val;
	}
	
	public static Status fromValue(Integer val){
		
		for(Status t : Status.values()){
			if(t.getValue().equals(val)){
				return t;
			}
		}
		if(null == val){
			return Status.NOT_STARTED;
		}
		throw new IllegalArgumentException(""+val);
	}
	
}
