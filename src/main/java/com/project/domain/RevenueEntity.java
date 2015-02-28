package com.project.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.commons.domain.BaseEntity;


@Entity
@Table(name="USER_REVENUES")
public class RevenueEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private Date transaction_time;
	
	private String transaction_time_string;
	
	private double amount;
	
    private Long category_name_id;
	
	private String description;
	
	private String coordLat;
	
	private String coordLng;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(Date transaction_time) {
		this.transaction_time = transaction_time;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransaction_time_string() {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Date today =  Calendar.getInstance().getTime(); 
		return ft.format(today);
	}

	public void setTransaction_time_string(String transaction_time_string) {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
		Date date = new Date();
		try {
			date = ft.parse(transaction_time_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.transaction_time = date;
		this.transaction_time_string = transaction_time_string;
	}

	public String getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(String coordLat) {
		this.coordLat = coordLat;
	}

	public String getCoordLng() {
		return coordLng;
	}

	public void setCoordLng(String coordLng) {
		this.coordLng = coordLng;
	}

	public Long getCategory_name_id() {
		return category_name_id;
	}

	public void setCategory_name_id(Long category_name_id) {
		this.category_name_id = category_name_id;
	}
	
	

}
