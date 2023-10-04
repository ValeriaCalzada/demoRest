package com.example.demo.services.exceptions;

import java.util.Date;

//create simple custom error details
public class CustomErrorDetails {

	private Date timestamp;
	private String message;
	private String errorDetails;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	
}
