package com.multimarkethub.orderservice.beans;

public class PaymentDetails {
	
	private Integer paymentId;
	private String paymentDate;
	private String paymentStatus;
	private String paymentMethod;

	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public PaymentDetails(Integer paymentId, String paymentDate, String paymentStatus, String paymentMethod) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}
	public PaymentDetails() {
		super();
	}
	
	
	
	

}
