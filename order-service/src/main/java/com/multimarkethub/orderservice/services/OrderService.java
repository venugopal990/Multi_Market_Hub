package com.multimarkethub.orderservice.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.multimarkethub.orderservice.beans.CheckOutResponse;
import com.multimarkethub.orderservice.beans.Orders;
import com.multimarkethub.orderservice.beans.PaymentType;

public interface OrderService {
	
	public CheckOutResponse cartCheckOut(Integer storeId, @RequestParam Integer customerId, PaymentType paymentType);

	public List<Orders> getOrders(Integer storeId, Integer customerId);

}
