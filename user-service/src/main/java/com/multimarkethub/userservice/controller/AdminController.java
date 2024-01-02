package com.multimarkethub.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.multimarkethub.userservice.beans.Admin;
import com.multimarkethub.userservice.beans.Response;
import com.multimarkethub.userservice.services.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	
	private final AdminService adminServices;
	
	@Autowired
	public AdminController(AdminService adminServices) {
		this.adminServices = adminServices;
	}
	
	
	@PostMapping("/admins")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Admin createAdmin(@Valid @RequestBody(required = true) Admin admin) {
		return adminServices.createAdmin(admin);
	}
	
	@GetMapping("/admins")
	public List<Admin> getAdmins() {
		
		return adminServices.getAdmins(null);
	}
	
	@GetMapping("/admins/{id}")
	public List<Admin> getAdmins(@PathVariable Integer id) {
		return adminServices.getAdmins(id);
	}
	
	
	@PutMapping("/admins/{adminId}/store/{storeId}")
	public void updateStoreId(@PathVariable Integer adminId, @PathVariable Integer storeId) {
		adminServices.updateStoreId(storeId, adminId);
	}
	
	
	@PutMapping("/admins")
	public Admin updateAdmin(@Valid @RequestBody Admin admin) {
		return adminServices.updateAdmin(admin);
	}
	
	@DeleteMapping("/admins/{id}")
	public String deleteById(@PathVariable(required = true) Integer id) {
		return adminServices.deleteAdminById(id);
	}
	
	@PostMapping("/admins/login")
	public ResponseEntity<Response> authenticateAdmin(@RequestParam(name = "email",required = true) String email,
			@RequestParam(name = "password",required = true)  String password) {
		Admin admin = adminServices.authenticateAdmin(email, password);
		if(admin !=null) {
			return ResponseEntity.ok(new Response(LocalDateTime.now(),true, "Login successful","{\"storeId\":"+admin.getStoreId()+",\"userId\":"+admin.getId()+"}"));
		}else {
			return ResponseEntity.status(401).body(new Response(LocalDateTime.now(),false, "Login failed. Invalid credentials",""));
		}
	}
	
	
	

}
