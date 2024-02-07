package com.multimarkethub.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multimarkethub.productservice.beans.ProductReponse;
import com.multimarkethub.productservice.beans.ProductRequest;
import com.multimarkethub.productservice.services.ImageService;
import com.multimarkethub.productservice.services.ProductService;
import com.multimarkethub.productservice.utils.Utils;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	
	private final ProductService productService;
	
    private final  ImageService imageService;
	
	@Autowired
	public ProductController(ProductService productService,ImageService imageService) {
		this.productService = productService;
		this.imageService = imageService; 
	}
	
	
	@PostMapping("/stores/{storeId}/products")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createProduct(@PathVariable Integer storeId, @Valid @RequestBody(required = true)ProductRequest productRequest) {
		productService.createProduct(storeId, productRequest);
	}
	
	@PostMapping("/stores/{storeId}/dummyproducts")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createDummyProduct(@PathVariable Integer storeId, @Valid @RequestBody(required = true) List<ProductRequest> productRequestList) {
		productService.createDummyProduct(storeId, productRequestList);
	}
	
	
	@GetMapping("/stores/{storeId}/products")
	public List<ProductReponse> getProducts(@PathVariable Integer storeId) {
		return productService.getProducts(storeId, null);
	}
	
	@GetMapping("/stores/{storeId}/products/{productId}")
	public List<ProductReponse> getProducts(@PathVariable Integer storeId, @PathVariable Integer productId) {
		return productService.getProducts(storeId, productId);
	}
	
	@GetMapping("/stores/{storeId}/products/search/{productName}")
	public List<ProductReponse> searchProducts(@PathVariable Integer storeId,@PathVariable String productName) {
		return productService.searchProducts(storeId, productName);
	}
	
	@GetMapping("/stores/{storeId}/categories/{categoryId}/products")
	public List<ProductReponse> getByProductsCategoryIdAndStoreId(@PathVariable Integer storeId,@PathVariable Integer categoryId) {
		return productService.getByProductsCategoryIdAndStoreId(storeId,categoryId);
	}
	
	@PostMapping("/stores/{storeId}/updateProductStock/{productId}")
	public void updateProductStock(@PathVariable(required = true) Integer storeId, @PathVariable(required = true) Integer productId, 
			@RequestParam(required = true) Integer stockQuantity) {
		 productService.updateProductStock(storeId,productId,stockQuantity);
	}
	
	
	@PutMapping("/stores/{storeId}/products/{productId}")
	public ProductReponse updateProduct(@PathVariable(required = true) Integer storeId, @PathVariable(required = true) Integer productId, @Valid @RequestBody(required = true)ProductRequest productRequest) {
		return productService.updateProduct(storeId,productId,productRequest);
	}
	
	@DeleteMapping("/stores/{storeId}/products/{productId}")
	public String deleteProductById(@PathVariable Integer storeId,  @PathVariable(required = true) Integer productId) {
		return productService.deleteProductById(storeId,productId);
	}
	
	
	@PostMapping("/products/images/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()) {
				if (file.getSize() <= 200 * 1024) {  // Checking file size, 200 KB limit
					if (Utils.isImage(file)) {
						String imageUrl = imageService.uploadImage(file);
						return new ResponseEntity<>(imageUrl, HttpStatus.OK);
					}else {
						return new ResponseEntity<>("Invalid file format. Only image files are allowed.", HttpStatus.BAD_REQUEST);
					}
				} else {
					return new ResponseEntity<>("File size exceeds the limit of 200 KB.", HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<>("No File Attached ", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to upload image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
