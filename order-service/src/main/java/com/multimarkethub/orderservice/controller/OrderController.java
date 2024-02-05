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
import com.multimarkethub.orderservice.services.EmailService;
import com.multimarkethub.orderservice.services.OrderService;
import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	private final OrderService orderService;
	private final EmailService emailService;
	
	public OrderController(OrderService orderService, EmailService emailService) {
		this.orderService =  orderService; 
		this.emailService = emailService;
	}
	
	
	@PostMapping("/checkout")
	public CheckOutResponse cartCheckOut(@RequestParam(required = true) Integer storeId, @RequestParam(required = true) Integer customerId, @Valid @RequestBody PaymentType paymentType) {
		
		return orderService.cartCheckOut(storeId, customerId,paymentType);
		
	}
	
	@GetMapping("/orders")
	public List<Orders> getOrders(@RequestParam(required = true) Integer storeId, @RequestParam(required = true) Integer customerId, @RequestParam(defaultValue = "0") Integer orderId) {
		
		return orderService.getOrders(storeId, customerId,orderId);
		
	}
	
	
	@PostMapping("/sendEmail")
	public String getOrders(@RequestParam(required = true) String toEmail, @RequestBody(required = true) String body, @RequestParam(required = true) String subject){
		
		return emailService.sendEmail(toEmail, body, subject);
		
	}
	
	
	@PostMapping("/verifyEmail")
    public String sendVerificationEmail(@RequestParam String emailAddress) {
		emailService.sendVerificationEmail(emailAddress);
        return "Verification email sent to: " + emailAddress;
    }
	
	
	@GetMapping("/verifiedEmailsFromAws")
    public List<String> verifiedEmails() {
		return emailService.getVerifiedEmails();
    }

}
