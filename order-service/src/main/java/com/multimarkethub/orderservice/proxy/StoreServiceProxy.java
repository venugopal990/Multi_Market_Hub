package com.multimarkethub.orderservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.multimarkethub.orderservice.beans.Store;

@FeignClient(name="store-service")
public interface StoreServiceProxy {
	
	
	@GetMapping("/stores/{storeId}")
    public List<Store> getStore(@PathVariable(required = true) Integer storeId);

}
