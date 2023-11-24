package com.multimarkethub.storeservice.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {


	private final BCryptPasswordEncoder passwordEncoder;

	public PasswordService(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public boolean authenticateUser(String enteredPassword, String storedPasswordHash) {
		return passwordEncoder.matches(enteredPassword, storedPasswordHash);
	}
	
	public String gethashedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	

}
