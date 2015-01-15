package com.project.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.commons.domain.BaseEntity;

@Entity
@Table(name="USER_BALANCE")
public class BalanceEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String username;
	private Date period_start;
	private Date period_end;
	private BigDecimal income;
	private BigDecimal expenditure;
	private BigDecimal balance;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getPeriod_start() {
		return period_start;
	}
	public void setPeriod_start(Date period_start) {
		this.period_start = period_start;
	}
	public Date getPeriod_end() {
		return period_end;
	}
	public void setPeriod_end(Date period_end) {
		this.period_end = period_end;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public BigDecimal getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
