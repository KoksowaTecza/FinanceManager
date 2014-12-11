package com.mvc.demo.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mvc.demo.commons.domain.BaseEntity;
/**
 * Entity to hold information about spittle
 * 
 * @author Dawid
 *
 */
@Entity
@Table (name="spittle")
public class Spittle extends BaseEntity{
	private static final long serialVersionUID = -4239826455139901340L;
	private Date when;
	private String text;
	private Spitter spitter;
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
