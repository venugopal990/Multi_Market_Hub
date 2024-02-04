package com.multimarkethub.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.orderservice.beans.CheckOutResponse;
import com.multimarkethub.orderservice.beans.Orders;
import com.multimarkethub.orderservice.beans.PaymentType;
import com.multimarkethub.orderservice.services.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService =  orderService; 
	}
	
	
	@PostMapping("/checkout")
	public CheckOutResponse cartCheckOut(@RequestParam(required = true) Integer storeId, @RequestParam(required = true) Integer customerId, @Valid @RequestBody PaymentType paymentType) {
		
		return orderService.cartCheckOut(storeId, customerId,paymentType);
		
	}
	
	@GetMapping("/orders")
	public List<Orders> getOrders(@RequestParam(required = true) Integer storeId, @RequestParam(required = true) Integer customerId) {
		
		return orderService.getOrders(storeId, customerId);
		
	}

}
