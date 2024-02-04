package com.multimarkethub.orderservice.beans;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PaymentType {
	
	@NotNull @Min(value = 1) @Max(value = 5)
	private Integer  paymentMethod ;

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
