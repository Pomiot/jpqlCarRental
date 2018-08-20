package com.capgemini.service;

import java.util.List;

import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.types.CarTO;

public interface CarService {

	
	CarTO addCar(CarTO car);

	void removeCar(Long carId);

	CarTO changeInformation(CarTO car);

	void assignWorker(Long carId, Long workerId);

	 List<CarTO> findCarByTypeAndBrand(CarType type, CarBrand brand);

	List<CarTO> findCarByType(CarType type);
	
	List<CarTO> findByWorker(Long workerId);
	
	CarTO findCarById(long id);
	
	}
