package com.multimarkethub.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="order-service")
public interface OrderServiceProxy {
	
	@PostMapping("/verifyEmail")
    public String sendVerificationEmail(@RequestParam String emailAddress);

}
