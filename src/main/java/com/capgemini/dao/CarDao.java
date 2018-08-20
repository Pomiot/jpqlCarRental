package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.CarEntity;
import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;

public interface CarDao extends Dao<CarEntity, Long> {

	List<CarEntity> findCarsByWorker(Long workerId);

	List<CarEntity> findCarsByType(CarType type);

	List<CarEntity> findCarsByTypeAndBrand(CarType type, CarBrand brand);

	List<CarEntity> findCar(CarBrand brand);
	
	List<CarEntity> findCarsRentedByMoreThan10DifferentClients();
	
	List<CarEntity> findCarsUsedInTimeInterval();

}
