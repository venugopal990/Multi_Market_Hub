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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.userservice.beans.Admin;
import com.multimarkethub.userservice.beans.LoginRequest;
import com.multimarkethub.userservice.beans.Response;
import com.multimarkethub.userservice.services.UserService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	
	private final UserService adminServices;
	
	@Autowired
	public AdminController(@Qualifier("adminServiceImpl") UserService adminServices) {
		this.adminServices = adminServices;
	}
	
	
	@PostMapping("/admins")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Admin createAdmin(@Valid @RequestBody(required = true) Admin admin) {
		return (Admin) adminServices.createUser(admin);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/admins")
	public List<Admin> getAdmins() {
		
		return (List<Admin>) adminServices.getUsers(null,null);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/admins/{id}")
	public List<Admin> getAdmins(@PathVariable Integer id) {
		return (List<Admin>) adminServices.getUsers(id,null);
	}
	
	
	@PutMapping("/admins/{adminId}/store/{storeId}")
	public void updateStoreId(@PathVariable Integer adminId, @PathVariable Integer storeId) {
		adminServices.updateStoreId(storeId, adminId);
	}
	
	
	@PutMapping("/admins")
	public Admin updateAdmin(@Valid @RequestBody Admin admin) {
		return (Admin) adminServices.updateUser(admin);
	}
	
	@DeleteMapping("/admins/{id}")
	public String deleteById(@PathVariable(required = true) Integer id) {
		return adminServices.deleteUserById(id);
	}
	
	@PostMapping("/admins/login")
	public ResponseEntity<Response> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {
		Admin admin = (Admin) adminServices.authenticateUser(loginRequest);
		if(admin !=null) {
			return ResponseEntity.ok(new Response(LocalDateTime.now(),true, "Login successful","{\"storeId\":"+admin.getStoreId()+",\"userId\":"+admin.getId()+"}"));
		}else {
			return ResponseEntity.status(401).body(new Response(LocalDateTime.now(),false, "Login failed. Invalid credentials",""));
		}
	}
	
	
	

}
