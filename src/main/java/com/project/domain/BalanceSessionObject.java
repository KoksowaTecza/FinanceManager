package com.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BalanceSessionObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String period_start;
	private String income;
	private String expenditure;
	private String balance;
	private String today;
	public String getPeriod_start() {
		return period_start;
	}
	public void setPeriod_start(String period_start) {
		this.period_start = period_start;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	
	
}
