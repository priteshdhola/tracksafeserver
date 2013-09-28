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
	private Integer age;
	private Integer weight;
	private Integer height;
	private Integer sex;
	
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
}
