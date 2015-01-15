package com.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CONFIGURATION_DATA")
public class ConfigurationData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String currency = "zloty";
	private BigDecimal startamount;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getStartamount() {
		return startamount;
	}
	public void setStartamount(BigDecimal startamount) {
		this.startamount = startamount;
	}
	
	
	
}
