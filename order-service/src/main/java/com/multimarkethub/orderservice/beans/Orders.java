package com.multimarkethub.orderservice.beans;

import java.util.List;

public class Orders {
	
	private Integer orderId;
	private String orderDateAndTime;
	private String orderUpdatedDateAndTime;
	private Double orderTotalAmount;
	private String orderStatus;
	private String orderDeliveryStatus;
	private List<OrderedProduct> OrderedProductList;
	private PaymentDetails paymentDetails;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderDateAndTime() {
		return orderDateAndTime;
	}
	public void setOrderDateAndTime(String orderDateAndTime) {
		this.orderDateAndTime = orderDateAndTime;
	}
	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDeliveryStatus() {
		return orderDeliveryStatus;
	}
	public void setOrderDeliveryStatus(String orderDeliveryStatus) {
		this.orderDeliveryStatus = orderDeliveryStatus;
	}
	public List<OrderedProduct> getOrderedProductList() {
		return OrderedProductList;
	}
	public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
		OrderedProductList = orderedProductList;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public String getOrderUpdatedDateAndTime() {
		return orderUpdatedDateAndTime;
	}
	public void setOrderUpdatedDateAndTime(String orderUpdatedDateAndTime) {
		this.orderUpdatedDateAndTime = orderUpdatedDateAndTime;
	}
	public Orders(Integer orderId, String orderDateAndTime,String orderUpdatedDateAndTime, Double orderTotalAmount, String orderStatus,
			String orderDeliveryStatus, List<OrderedProduct> orderedProductList, PaymentDetails paymentDetails) {
		super();
		this.orderId = orderId;
		this.orderDateAndTime = orderDateAndTime;
		this.orderUpdatedDateAndTime = orderUpdatedDateAndTime;
		this.orderTotalAmount = orderTotalAmount;
		this.orderStatus = orderStatus;
		this.orderDeliveryStatus = orderDeliveryStatus;
		OrderedProductList = orderedProductList;
		this.paymentDetails = paymentDetails;
	}
	public Orders() {
		super();
	}
	
	
	

}
