package com.multimarkethub.storeservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Store> createStore(@Valid @RequestBody Store store){
    	Store storeResult = storeService.createStore(store);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(storeResult.getId())
						.toUri();
		return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/stores")
    public List<Store> getStore() {
    	return storeService.getStores(null);
    }

    @GetMapping("/stores/{storeId}")
    public List<Store> getStore(@PathVariable(required = true) Integer storeId) {
    	return storeService.getStores(storeId);
    }

}
