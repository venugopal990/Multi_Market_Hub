package com.multimarkethub.orderservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.multimarkethub.orderservice.beans.Customer;



@FeignClient(name="user-service")
public interface UserServiceProxy {
	
	@PostMapping("/updateVerifiedEmail")
	public void updateVerifiedEmail(@RequestBody List<String> emailsList);
	
	
	@GetMapping("/customers/{id}")
	public List<Customer> getCustomers(@PathVariable Integer id,@RequestParam(required = true) Integer storeId);
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(@RequestParam(required = true) Integer storeId);

}
