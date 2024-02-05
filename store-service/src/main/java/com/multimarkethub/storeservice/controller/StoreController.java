package com.multimarkethub.storeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.storeservice.beans.Store;
import com.multimarkethub.storeservice.services.StoreService;

import jakarta.validation.Valid;

@RestController
public class StoreController {
	
	
	private final StoreService storeService;
	
	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

    @PostMapping("/stores")
    public Store createStore(@Valid @RequestBody Store store){
    	return storeService.createStore(store);
    }
    
    
    @GetMapping("/storeName/{resourceName}")
    public Store getStore(@PathVariable(required = true) String resourceName) {
    	return storeService.getStoresDetailsByResourceName(resourceName);
    }
    
    @GetMapping("/stores")
    public List<Store> getStore() {
    	return storeService.getStores(null);
    }

    @GetMapping("/stores/{storeId}")
    public List<Store> getStore(@PathVariable(required = true) Integer storeId) {
    	return storeService.getStores(storeId);
    }
    
    
    @GetMapping("/storesById/{storeId}")
    public Boolean getStoreById(@PathVariable(required = true) Integer storeId) {
    	return !storeService.getStores(storeId).isEmpty();
    }

}
