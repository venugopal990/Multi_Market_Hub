package com.multimarkethub.orderservice.beans;

public class CheckOutResponse {
	
	private Integer orderId;
	private String orderStatus;
	private String message;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CheckOutResponse(Integer orderId, String orderStatus, String message) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.message = message;
	}

}
