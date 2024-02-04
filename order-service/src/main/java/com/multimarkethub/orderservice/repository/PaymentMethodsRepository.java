package com.multimarkethub.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multimarkethub.orderservice.entity.PaymentMethodsEntity;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethodsEntity, Integer>{

}
