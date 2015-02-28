package com.project.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.commons.domain.BaseEntity;

@Entity
@Table(name="USER_EXPENSE_PROJECTION")
public class ExpenseProjection extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String username;
	private Long category_name_id;
	private String category_name;
	private Long user_balance_id;
	private double amount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getCategory_name_id() {
		return category_name_id;
	}
	public void setCategory_name_id(Long category_name_id) {
		this.category_name_id = category_name_id;
	}
	public Long getUser_balance_id() {
		return user_balance_id;
	}
	public void setUser_balance_id(Long user_balance_id) {
		this.user_balance_id = user_balance_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	

}
