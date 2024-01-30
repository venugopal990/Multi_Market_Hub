package com.multimarkethub.orderservice.beans;

import java.time.LocalDateTime;

public class Response {
	
    private boolean success;
    private String message;
	private String details;
	private LocalDateTime timeStamp;

	public Response(LocalDateTime timeStamp, boolean success, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.success = success;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

   
}