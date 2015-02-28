package com.project.domain;

import java.math.BigDecimal;

public class TransactionPeriodSummary {
	private BigDecimal cat_id;
	private String cat_name;
	private BigDecimal sum;
	public BigDecimal getCat_id() {
		return cat_id;
	}
	public void setCat_id(BigDecimal cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	

}
