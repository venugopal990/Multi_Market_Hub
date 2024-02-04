package com.multimarkethub.orderservice.beans;

public class AddToCartResponse {
	
	private Integer cartId;
	private Integer productId;
	private Double productTotalPrice;
	private Integer quantity;
	private Double cartTotalprice;
	private Integer noOfProducts;
	private Integer productStockQuantity;
	
	public AddToCartResponse(Integer cartId,Integer productId,Integer quantity, Double productTotalPrice, Double cartTotalprice,Integer noOfProducts,Integer productStockQuantity) {
		this.cartTotalprice=cartTotalprice;
		this.cartId = cartId;
		this.productId = productId;
		this.productTotalPrice = productTotalPrice;
		this.quantity = quantity;
		this.noOfProducts =  noOfProducts;
		this.productStockQuantity =  productStockQuantity;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Double getCartTotalprice() {
		return cartTotalprice;
	}

	public void setCartTotalprice(Double cartTotalprice) {
		this.cartTotalprice = cartTotalprice;
	}
	
	public Integer getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(Integer noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	public Integer getProductStockQuantity() {
		return productStockQuantity;
	}
	public void setProductStockQuantity(Integer productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}
	
}
