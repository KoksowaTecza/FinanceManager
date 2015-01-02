package com.project.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="profile_image")
public class ProfileImage implements Serializable{
	private static final long serialVersionUID = -9019153772756036755L;
	@Id
	private String profile_image_name;
	@Lob
	private Blob profile_image;
	private String profile_image_content_type;
	public String getProfile_image_name() {
		return profile_image_name;
	}
	public void setProfile_image_name(String profile_image_name) {
		this.profile_image_name = profile_image_name;
	}
	public Blob getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(Blob profile_image) {
		this.profile_image = profile_image;
	}
	public String getProfile_image_content_type() {
		return profile_image_content_type;
	}
	public void setProfile_image_content_type(String profile_image_content_type) {
		this.profile_image_content_type = profile_image_content_type;
	}
	
	
}
