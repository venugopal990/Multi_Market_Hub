package com.multimarkethub.orderservice.beans;

import java.io.Serializable;

public class OrderedProduct implements Serializable {
	
	private Integer productId;
	private String productName;
	private String productImageUrl;
	private Integer quantity;
	private Double price;
	private Double productOriginalprice;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getProductOriginalprice() {
		return productOriginalprice;
	}
	public void setProductOriginalprice(Double productOriginalprice) {
		this.productOriginalprice = productOriginalprice;
	}
	public OrderedProduct(Integer productId, String productName, String productImageUrl, Integer quantity,
			Double price, Double productOriginalprice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImageUrl = productImageUrl;
		this.quantity = quantity;
		this.price = price;
		this.productOriginalprice = productOriginalprice;
	}
	public OrderedProduct() {
		super();
	}
	
	
	
	
	

}
