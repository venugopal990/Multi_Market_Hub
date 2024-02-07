package com.multimarkethub.orderservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.orderservice.beans.PaymentMethods;
import com.multimarkethub.orderservice.beans.PaymentType;
import com.multimarkethub.orderservice.entity.PaymentMethodsEntity;
import com.multimarkethub.orderservice.entity.PaymentsEntity;
import com.multimarkethub.orderservice.repository.PaymentMethodsRepository;
import com.multimarkethub.orderservice.repository.PaymentsRepository;

@Service
public class PaymentServiceImpl  implements PaymentService{
	
	private final PaymentMethodsRepository paymentMethodsRepository;
	
	private final PaymentsRepository paymentsRepository; 
	
	@Autowired
	public PaymentServiceImpl(PaymentMethodsRepository paymentMethodsRepository,PaymentsRepository paymentsRepository) {
		this.paymentMethodsRepository =  paymentMethodsRepository;
		this.paymentsRepository = paymentsRepository;
	}

	@Override
	public List<PaymentMethods> getPaymentMethods() {
		
		List<PaymentMethodsEntity> paymentMethodsEntityList = paymentMethodsRepository.findAll();
		return convertEntityListToPaymentMethods(paymentMethodsEntityList);
		
	}
	
	
	private List<PaymentMethods> convertEntityListToPaymentMethods(List<PaymentMethodsEntity> paymentMethodsEntityList) {
		
		List<PaymentMethods> paymentMethodsList = new ArrayList<>();
		for(PaymentMethodsEntity paymentMethodsEntity :paymentMethodsEntityList) {
			paymentMethodsList.add(new PaymentMethods(paymentMethodsEntity.getMethodId(), paymentMethodsEntity.getMethodName()));
		}
		return paymentMethodsList;
	}
	
	@Override
	public Integer savePayments(Integer orderId, Integer storeId, Double totalAmount ,PaymentType paymentType) {
		
		Integer paymentstatus = paymentType.getPaymentMethod()==5?1:2;
		PaymentsEntity paymentsEntity= new PaymentsEntity(orderId, paymentsRepository.findCurrentTimeStamp(), totalAmount, paymentstatus, paymentType.getPaymentMethod()
				, storeId);
		return paymentsRepository.save(paymentsEntity).getPaymentId();
	}
	
	
	@Override
	public void updatePaymentStatus(Integer orderId, Integer paymentStatusId, Integer storeId) {
		paymentsRepository.updateProductStock(orderId, paymentStatusId, storeId);
	}

}
