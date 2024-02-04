package com.multimarkethub.orderservice.beans;


public class PaymentMethods {
	
	
	private Integer methodId;

	private String methodName;

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public PaymentMethods(Integer methodId, String methodName) {
		super();
		this.methodId = methodId;
		this.methodName = methodName;
	}

	public PaymentMethods() {
		super();
	}
	
	
	

}
