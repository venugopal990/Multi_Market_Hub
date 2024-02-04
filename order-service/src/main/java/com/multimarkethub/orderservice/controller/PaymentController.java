package com.multimarkethub.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.orderservice.beans.PaymentMethods;
import com.multimarkethub.orderservice.services.PaymentService;

@RestController
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService =  paymentService;
	}
	
	
	@GetMapping("/paymentMethods")
	public List<PaymentMethods> getPaymentMethods() {
		
		return paymentService.getPaymentMethods();
		
	}

}
