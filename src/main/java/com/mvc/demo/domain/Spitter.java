package com.mvc.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mvc.demo.commons.domain.BaseEntity;

@Entity
@Table(name="spitter")
public class Spitter extends BaseEntity {
	private static final long serialVersionUID = 5351079363353906417L;
	private String userFullName;
	private String username;
	private String password;
	private String email;
	private String profile_image;

	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
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
	
	
}
