package com.multimarkethub.orderservice.beans;

public class AddToCartResponse extends CartPrice {
	
	private Integer productId;
	private Double productTotalPrice;
	
	public AddToCartResponse(Integer productId, Double productTotalPrice, Double cartTotalprice) {
		super(cartTotalprice);
		this.productId = productId;
		this.productTotalPrice = productTotalPrice;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(Double productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	
}
