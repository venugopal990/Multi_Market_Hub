package com.multimarkethub.productservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="store-service")
public interface StoreServiceProxy {
	
	
	@GetMapping("/storesById/{storeId}")
    public Boolean getStoreById(@PathVariable(required = true) Integer storeId);

}
