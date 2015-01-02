package com.project.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSessionObject implements Serializable {
	private static final long serialVersionUID = 5137032661006129669L;
	private int id;
	private String userFullName;
	private String username;
	private String password;
	private String email;
	private String profile_image_name;
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfile_image_name() {
		return profile_image_name;
	}
	public void setProfile_image_name(String profile_image_name) {
		this.profile_image_name = profile_image_name;
	}
	
	

}
