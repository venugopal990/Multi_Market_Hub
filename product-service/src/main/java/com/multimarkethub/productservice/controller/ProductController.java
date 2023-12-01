package com.multimarkethub.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.productservice.beans.ProductReponse;
import com.multimarkethub.productservice.beans.ProductRequest;
import com.multimarkethub.productservice.services.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@PostMapping("/stores/{storeId}/products")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createProduct(@PathVariable Integer storeId, @Valid @RequestBody(required = true)ProductRequest productRequest) {
		productService.createProduct(storeId, productRequest);
	}
	
	
	@GetMapping("/stores/{storeId}/products")
	public List<ProductReponse> getProducts(@PathVariable Integer storeId) {
		return productService.getProducts(storeId, null);
	}
	
	@GetMapping("/stores/{storeId}/products/{productId}")
	public List<ProductReponse> getProducts(@PathVariable Integer storeId, @PathVariable Integer productId) {
		return productService.getProducts(storeId, productId);
	}
	
	@GetMapping("/stores/{storeId}/categories/{categoryId}/products")
	public List<ProductReponse> getByProductsCategoryIdAndStoreId(@PathVariable Integer storeId,@PathVariable Integer categoryId) {
		return productService.getByProductsCategoryIdAndStoreId(storeId,categoryId);
	}
	
	
	@PutMapping("/stores/{storeId}/products/{productId}")
	public ProductReponse updateProduct(@PathVariable Integer storeId, @PathVariable(required = true) Integer productId, @Valid @RequestBody(required = true)ProductRequest productRequest) {
		return productService.updateProduct(storeId,productId,productRequest);
	}
	
	@DeleteMapping("/stores/{storeId}/products/{productId}")
	public String deleteProductById(@PathVariable Integer storeId,  @PathVariable(required = true) Integer productId) {
		return productService.deleteProductById(storeId,productId);
	}

}
