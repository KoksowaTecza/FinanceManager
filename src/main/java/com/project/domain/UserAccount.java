package com.project.domain;


import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.project.commons.domain.BaseEntity;

@Entity
@Table(name="UserAccount")
public class UserAccount extends BaseEntity {
	private static final long serialVersionUID = 5351079363353906417L;
	@Size(min=3, max=50, message="Your full name must be between 3 and 50 characters long.")
	private String userFullName;
	@Size(min=3, max=20, message="Username must be between 3 and 20 characters long.")
	@Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces")
	private String username;
	@Size(min=6, max=20, message="The password must be at least 6 characters long.")
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
	public String getProfile_image_name() {
		return profile_image_name;
	}
	public void setProfile_image_name(String profile_image_name) {
		this.profile_image_name = profile_image_name;
	}
	
}
