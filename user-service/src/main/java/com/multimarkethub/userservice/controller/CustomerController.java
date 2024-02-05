package com.multimarkethub.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.multimarkethub.userservice.beans.Customer;
import com.multimarkethub.userservice.beans.LoginRequest;
import com.multimarkethub.userservice.beans.Response;
import com.multimarkethub.userservice.services.UserService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {


	private final UserService customerServices;

	@Autowired
	public CustomerController(@Qualifier("customerServiceImpl") UserService customerServices) {
		this.customerServices = customerServices;
	}


	@PostMapping("/customers")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer createCustomer(@Valid @RequestBody(required = true) Customer customer,@RequestParam(required = true) Integer storeId) {
		customer.setStoreId(storeId);
		return (Customer) customerServices.createUser(customer);
	}
	
	
	@PostMapping("/updateVerifiedEmail")
	public void updateVerifiedEmail(@RequestBody List<String> emailsList) {
		customerServices.updateVerifiedEmail(emailsList);
	}


	@SuppressWarnings("unchecked")
	@GetMapping("/customers")
	public List<Customer> getCustomers(@RequestParam(required = true) String storeId) {
		return (List<Customer>) customerServices.getUsers(null);
	}

	/**
	 * TODO Add StoreId in the condition
	 * @param id
	 * @param storeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/customers/{id}")
	public List<Customer> getCustomers(@PathVariable Integer id,@RequestParam(required = true) Integer storeId) {
		return (List<Customer>) customerServices.getUsers(id);
	}


	/**
	 * TODO Add StoreId in the condition
	 * @param customer
	 * @param storeId
	 * @return
	 */
	@PutMapping("/customers")
	public Customer updateCustomer(@Valid @RequestBody Customer customer,@RequestParam(required = true) Integer storeId) {
		customer.setStoreId(storeId);
		return (Customer) customerServices.updateUser(customer);
	}

	/**
	 * TODO Add StoreId in the condition
	 * @param id
	 * @param storeId
	 * @return
	 */
	@DeleteMapping("/customers/{id}")
	public String deleteById(@PathVariable(required = true) Integer id, @RequestParam(required = true) Integer storeId) {
		return customerServices.deleteUserById(id);
	}

	
	@PostMapping("/customers/login")
	public ResponseEntity<Response> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest){
		Customer customer = (Customer) customerServices.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
		if(customer != null) {
			return ResponseEntity.ok(new Response(LocalDateTime.now(),true, "Login successful","{\"storeId\":"+customer.getStoreId()+",\"CustomerId\":"+customer.getId()+"}"));
		}else {
			return ResponseEntity.status(401).body(new Response(LocalDateTime.now(),false, "Login failed. Invalid credentials",""));
		}
	}

}
