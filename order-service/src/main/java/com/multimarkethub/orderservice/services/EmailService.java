package com.multimarkethub.orderservice.services;

import java.util.List;


public interface EmailService {
	
	public String sendEmail(String toEmail, String body, String subject);

	public void sendVerificationEmail(String emailAddress);

	public List<String> getVerifiedEmails();

}
