package com.project.domain;

public class JsonResponse {
	private String status = null;

	private Object result = null;
	
	private  Object addings = null;

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}

	public Object getResult() {

		return result;

	}

	public void setResult(Object result) {

		this.result = result;

	}

	public Object getAddings() {
		return addings;
	}

	public void setAddings(Object addings) {
		this.addings = addings;
	}

}
