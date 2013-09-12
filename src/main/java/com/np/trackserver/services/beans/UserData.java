package com.np.trackserver.services.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User model which maps to database User object
 * @author npatel
 *
 */
@XmlRootElement(name="userData")
public class UserData {
	
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private short age;
	private short weight;
	private short height;
	private short sex;
	
	public UserData() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public short getWeight() {
		return weight;
	}
	public void setWeight(short weight) {
		this.weight = weight;
	}
	public short getHeight() {
		return height;
	}
	public void setHeight(short height) {
		this.height = height;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	
}
