package com.multimarkethub.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.multimarkethub.orderservice.beans.AddToCartResponse;
import com.multimarkethub.orderservice.beans.Cart;
import com.multimarkethub.orderservice.beans.AddToCartRequest;
import com.multimarkethub.orderservice.services.CartService;

import jakarta.validation.Valid;

@RestController
public class CartController {

	private final CartService addToCartService;

	@Autowired
	public CartController(CartService addToCartService) {
		this.addToCartService = addToCartService;
	}

	@PostMapping("/cartItems")
	public AddToCartResponse addItemToCart(@Valid @RequestBody AddToCartRequest addToCartRequest) {
		return addToCartService.addItemToCart(addToCartRequest);
	}
	
	
	@GetMapping("/cartItems")
	public Cart getItemsFromCart(@RequestParam Integer storeId, @RequestParam Integer customerId) {
		return addToCartService.getItemsFromCart(storeId,customerId);
	}

	@DeleteMapping("/cartItems/{productId}")
	public AddToCartResponse removeItemFromCart(@RequestParam Integer storeId, @RequestParam Integer customerId, @PathVariable("productId") Integer productId) {
		return addToCartService.removeItemFromCart(storeId,customerId,productId);
	}
}
