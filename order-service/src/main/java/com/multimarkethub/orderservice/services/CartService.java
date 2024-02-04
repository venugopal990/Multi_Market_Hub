package com.multimarkethub.orderservice.services;

import com.multimarkethub.orderservice.beans.AddToCartResponse;
import com.multimarkethub.orderservice.beans.Cart;
import com.multimarkethub.orderservice.entity.CartsEntity;
import com.multimarkethub.orderservice.beans.AddToCartRequest;

public interface CartService {
	
	
	public AddToCartResponse addItemToCart(AddToCartRequest cart);
	public Cart getItemsFromCart(Integer storeId, Integer customerId);
	public AddToCartResponse removeItemFromCart(Integer storeId, Integer customerId, Integer productId);
	public CartsEntity getByCustomerIdAndStoreId(Integer customerId, Integer storeId);

}
