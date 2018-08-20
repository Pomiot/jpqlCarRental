package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Autowired
	private WorkerDao workerDao;

	@Override
	public CarTO addCar(CarTO car) {
		CarEntity carEnitity = carDao.save(CarMapper.toCarEntity(car));

		return CarMapper.toCarTO(carEnitity);
	}

	@Override
	public void removeCar(Long carId) {
		carDao.delete(carId);
	}

	@Override
	public CarTO changeInformation(CarTO car) {
		CarEntity carEntity = carDao.update(CarMapper.toCarEntity(car));
		return CarMapper.toCarTO(carEntity);
	}

	@Override
	public void assignWorker(Long carId, Long workerId) {
		CarEntity car = carDao.getOne(carId);
		WorkerEntity worker = workerDao.getOne(workerId);

		car.getWorkers().add(worker);
		worker.getCars().add(car);

		carDao.update(car);
		workerDao.update(worker);
	}

	@Override
	public List<CarTO> findCarByType(CarType type) {
		List<CarEntity> cars = carDao.findCarsByType(type);
		return CarMapper.map2TOs(cars);
	}

	@Override
	public List<CarTO> findCarByTypeAndBrand(CarType type, CarBrand brand) {
		List<CarEntity> cars = carDao.findCarsByTypeAndBrand(type, brand);
		return CarMapper.map2TOs(cars);
	}

	@Override
	public List<CarTO> findByWorker(Long workerId) {
		List<CarEntity> cars = carDao.findCarsByWorker(workerId);
		return CarMapper.map2TOs(cars);
	}

	@Override
	public CarTO findCarById(long id) {
		CarEntity car = carDao.findOne(id);
		return CarMapper.toCarTO(car);
	}

}
