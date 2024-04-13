package com.multimarkethub.orderservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesResult;
import com.amazonaws.services.simpleemail.model.ListIdentitiesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentitiesResult;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.multimarkethub.orderservice.proxy.UserServiceProxy;

@Service
public class EmailServiceimpl implements EmailService {
	
    
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final UserServiceProxy userServiceProxy;
    
    @Autowired
    public EmailServiceimpl( AmazonSimpleEmailService amazonSimpleEmailService, UserServiceProxy userServiceProxy) {
    	this.amazonSimpleEmailService = amazonSimpleEmailService;
    	this.userServiceProxy = userServiceProxy;
    }
    
    
    @Value("${aws.email.From}")
    private String fromEmail;
    


	@Override
	public String sendEmail(String toEmail, String body, String subject)  {
		SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(toEmail))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8")
                                        .withData(body))
                                .withText(new Content()
                                        .withCharset("UTF-8")
                                        .withData("Hello,\nThis is a test email sent using AWS SES.")))
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData(subject)))
                .withSource(fromEmail);

		SendEmailResult sendEmailResult  = amazonSimpleEmailService.sendEmail(request);
		System.out.println(sendEmailResult.toString());
        System.out.println("Email sent successfully!");
        return "Email sent successfully!";
		
	}
	
	
	
	
	@Override
	public String sendVerificationEmail(String emailAddress) {
		if(!getAwsVerifiedEmails().contains(emailAddress)) {
			VerifyEmailAddressRequest verifyRequest = new VerifyEmailAddressRequest().withEmailAddress(emailAddress);
			amazonSimpleEmailService.verifyEmailAddress(verifyRequest);
			return "Verification email sent to: " + emailAddress;
		}else {
			return emailAddress+" already Verified. Thank you";
		}
	}
	
	
	private List<String> getAwsVerifiedEmails() {
		List<String> verifiedEmails = new ArrayList<>();
        ListIdentitiesRequest listIdentitiesRequest = new ListIdentitiesRequest();
		listIdentitiesRequest.setIdentityType("EmailAddress");
        ListIdentitiesResult result = amazonSimpleEmailService.listIdentities(listIdentitiesRequest);
        for (String identity : result.getIdentities()) {
            GetIdentityVerificationAttributesRequest verificationAttributesRequest =
                new GetIdentityVerificationAttributesRequest().withIdentities(identity);
            GetIdentityVerificationAttributesResult verificationAttributesResult =
                amazonSimpleEmailService.getIdentityVerificationAttributes(verificationAttributesRequest);
            
            // Check if the email is verified
            if (verificationAttributesResult.getVerificationAttributes().get(identity).getVerificationStatus().equals("Success")) {
                verifiedEmails.add(identity);
            }
        }
        userServiceProxy.updateVerifiedEmail(verifiedEmails);
        return verifiedEmails;
        
        

	}
	
	
	@Override
	public List<String> getVerifiedEmails() {
		List<String> emailList = getAwsVerifiedEmails();
        return emailList;
	}

}
