package com.multimarkethub.orderservice.beans;

public class CartPrice {
	
	private Double cartTotalprice;

	public CartPrice(Double cartTotalprice) {
		super();
		this.cartTotalprice = cartTotalprice;
	}

	public Double getCartTotalprice() {
		return cartTotalprice;
	}

	public void setCartTotalprice(Double cartTotalprice) {
		this.cartTotalprice = cartTotalprice;
	}

}
