package com.multimarkethub.orderservice.services;

import java.util.List;

import com.multimarkethub.orderservice.beans.PaymentMethods;
import com.multimarkethub.orderservice.beans.PaymentType;

public interface PaymentService {

	List<PaymentMethods> getPaymentMethods();

	Integer savePayments(Integer orderId, Integer storeId, Double totalAmount, PaymentType paymentType);

}
