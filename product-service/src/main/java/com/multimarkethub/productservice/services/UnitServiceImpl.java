package com.multimarkethub.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.productservice.beans.Unit;
import com.multimarkethub.productservice.entity.UnitsEntity;
import com.multimarkethub.productservice.exception.NotFoundException;
import com.multimarkethub.productservice.repository.UnitsRepository;


@Service
public class UnitServiceImpl implements UnitService{
	
	
	private final UnitsRepository unitsRepository;
	
	@Autowired
	public UnitServiceImpl(UnitsRepository unitsRepository) {
		this.unitsRepository =  unitsRepository;
	}

	@Override
	public List<Unit> getUnits(Integer unitId)  {
		List<UnitsEntity> unitsEntityList = new ArrayList<UnitsEntity>();
		if(unitId == null) {
			unitsEntityList = unitsRepository.findAll();
		}else {
			Optional<UnitsEntity>  UnitsEntityOptional = unitsRepository.findById(unitId);
			if(UnitsEntityOptional.isPresent()) {
				unitsEntityList.add(UnitsEntityOptional.get());
			}
		}

		if(unitsEntityList.isEmpty()) {
			throw new NotFoundException("No Units found. The requested operation cannot be completed.");
		}else {

			return mapToUnitsBean(unitsEntityList);
		}
	}
	
	
	private List<Unit> mapToUnitsBean(List<UnitsEntity> unitsEntityList) {
		
		List<Unit> unitList =  new ArrayList<>();
		
		for(UnitsEntity entity : unitsEntityList ) {
			
			unitList.add(new Unit(entity.getUnitId(), entity.getUnitName(), entity.getUnitAbbreviation()));
		}
		
		
		return unitList;
		
	}
	
	
	
	

}
