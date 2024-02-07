package com.multimarkethub.orderservice.services;

import java.util.List;


public interface EmailService {
	
	public String sendEmail(String toEmail, String body, String subject);

	public String sendVerificationEmail(String emailAddress);

	public List<String> getVerifiedEmails();

}
