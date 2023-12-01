package com.multimarkethub.productservice.services;

import java.util.List;

import com.multimarkethub.productservice.beans.Unit;


public interface UnitService {

	public List<Unit> getUnits(Integer unitId);

}
