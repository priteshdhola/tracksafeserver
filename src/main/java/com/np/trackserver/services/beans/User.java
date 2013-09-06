package com.np.trackserver.services.beans;

/**
 * User model which maps to database User object
 * @author npatel
 *
 */
public class User {
	
	private int id;
	private String userName;
	private String email;
	private String password;
	private short age;
	private short weight;
	private short height;
	private short sex;
	
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
