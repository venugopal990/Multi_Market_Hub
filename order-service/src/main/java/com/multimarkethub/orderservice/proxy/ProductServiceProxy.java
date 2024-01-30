package com.multimarkethub.orderservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.multimarkethub.orderservice.beans.ProductReponse;


@FeignClient(name="product-service")
public interface ProductServiceProxy {
	
	
	@GetMapping("/stores/{storeId}/products/{productId}")
	public List<ProductReponse> getProducts(@PathVariable Integer storeId, @PathVariable Integer productId);

}
