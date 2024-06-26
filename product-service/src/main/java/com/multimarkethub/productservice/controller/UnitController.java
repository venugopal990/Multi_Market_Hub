package com.multimarkethub.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.multimarkethub.productservice.beans.Unit;
import com.multimarkethub.productservice.services.UnitService;


@RestController
public class UnitController {
	
	private final UnitService unitService;
	
	@Autowired
	public UnitController(UnitService unitService) {
		this.unitService = unitService;
	}
	
	@Cacheable("units")
	@GetMapping("/units")
	public List<Unit> getUnits() {
		
		return unitService.getUnits(null);
	}
	
	@Cacheable(value = "units", key = "'unitId-' + #unitId", condition = "#units != null")
	@GetMapping("/units/{unitId}")
	public List<Unit> getUnits(@PathVariable Integer unitId) {
		
		return unitService.getUnits(unitId);
	}

}
