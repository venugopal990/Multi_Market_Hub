package com.multimarkethub.orderservice.beans;

import java.util.List;

public class Cart {
	
	private Integer noOfProducts;
	private Double cartTotalPrice;
	private List<ProductReponse> productReponseList;
	

	public Cart(Integer noOfProducts, Double cartTotalPrice, List<ProductReponse> productReponseList) {
		super();
		this.noOfProducts = noOfProducts;
		this.cartTotalPrice = cartTotalPrice;
		this.productReponseList = productReponseList;
	}
	public Integer getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(Integer noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	public Double getCartTotalPrice() {
		return cartTotalPrice;
	}
	public void setCartTotalPrice(Double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	public List<ProductReponse> getProductReponseList() {
		return productReponseList;
	}
	public void setProductReponseList(List<ProductReponse> productReponseList) {
		this.productReponseList = productReponseList;
	}
	

}
