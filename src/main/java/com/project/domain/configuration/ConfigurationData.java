package com.project.domain.configuration;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigurationData {
	private String waluta = "zloty";
	@NotEmpty(message="Wartosc nie moze byc pusta")
	private String startAmount;
	
	public String getWaluta() {
		return waluta;
	}
	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}
	public String getStartAmount() {
		return startAmount;
	}
	public void setStartAmount(String startAmount) {
		this.startAmount = startAmount;
	}
	
	
}
