package com.project.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USER_CATEGORIES_REVENUES")
public class CategoryRevenueEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message="To pole jest wymagane")
	@Id
	private String category_name;
	private String username;
	private String icon_name;
	private String category_desc;
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
