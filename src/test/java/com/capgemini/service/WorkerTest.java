package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BranchDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.enums.Color;
import com.capgemini.enums.Position;
import com.capgemini.types.BranchTO;
import com.capgemini.types.BranchTO.BranchTOBuilder;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.WorkerTO;
import com.capgemini.types.WorkerTO.WorkerTOBuilder;

import model.Address;
import model.EmployeeSearchCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
public class WorkerTest {

	@Autowired
	public BranchService branchService;
	
	@Autowired
	public WorkerService workerService;
	
	@Autowired
	public CarService carService;
	
	@Autowired
	public BranchDao branchDao;
	
	@Autowired 
	public WorkerDao workerDao;
	
	@Autowired
	public CarDao carDao;
	
	
	
	@Test
	public void shouldFindAllWorkersByBranchAndByCar(){
		BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
				.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
		WorkerTO worker1 = new WorkerTOBuilder().withFirstName("Jan").withLastName("Kowalski")
				.withPosition(Position.MANAGER).build();
		WorkerTO worker2 = new WorkerTOBuilder().withFirstName("Anna").withLastName("Ludwiczak")
				.withPosition(Position.ACCOUNTANT).build();
		WorkerTO worker3 = new WorkerTOBuilder().withFirstName("Adam").withLastName("Bukowski")
				.withPosition(Position.SALESMAN).build();
		WorkerTO worker4 = new WorkerTOBuilder().withFirstName("Joanna").withLastName("Nowakowska")
				.withPosition(Position.SALESMAN).build();
		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();


		BranchTO addedBranch = branchService.addBranch(branch);
		WorkerTO addedWorker1 = workerService.addWorker(worker1);
		WorkerTO addedWorker2 = workerService.addWorker(worker2);
		WorkerTO addedWorker3 = workerService.addWorker(worker3);
		WorkerTO addedWorker4 = workerService.addWorker(worker4);
		CarTO addedCar = carService.addCar(car);
		
		branchService.addWorkerToBranch(addedWorker1.getId(), addedBranch.getId());
		branchService.addWorkerToBranch(addedWorker2.getId(), addedBranch.getId());
		branchService.addWorkerToBranch(addedWorker3.getId(), addedBranch.getId());
		branchService.addWorkerToBranch(addedWorker4.getId(), addedBranch.getId());
		
		carService.assignWorker(addedCar.getId(), addedWorker1.getId());
		carService.assignWorker(addedCar.getId(), addedWorker4.getId());
		
		
		//when
		List<WorkerTO> workers = branchService.findAllWorkersByBranchAndByCar(addedBranch.getId(), addedCar.getId());
		
		assertNotNull(workers);
		assertEquals(2, workers.size());
		assertEquals("Kowalski", workers.get(0).getLastName());
		assertEquals("Nowakowska", workers.get(1).getLastName());
	}
	
	@Test
	public void shouldFindWorkerByBranchOrCarOrPosition(){
		
		//given
		BranchEntity branch = new BranchEntity("branch@gmail.com", "159263487",
				new Address("Poznan", "Kolorowa", "1459"));
		BranchEntity addedBranch = branchDao.save(branch);

		CarEntity car = new CarEntity(CarType.SUV, CarBrand.FORD, 2015, Color.BLACK);
		CarEntity addedCar = carDao.save(car);

		WorkerEntity worker = new WorkerEntity("Jan", "Nowak", Position.SALESMAN);
		WorkerEntity addedWorker = workerDao.save(worker);
		
		addedBranch.getWorkers().add(addedWorker);
		addedCar.getWorkers().add(addedWorker);
		addedWorker.getCars().add(addedCar);
		addedWorker.setBranch(addedBranch);
		
		// when
		EmployeeSearchCriteria parameters = new EmployeeSearchCriteria(addedBranch, null, Position.SALESMAN);
		
		List<WorkerEntity> workers = workerDao.findWorkersByBranchOrCarOrPosition(parameters);
		
		assertEquals(1, workers.size());
		assertEquals(addedWorker, workers.get(0) );
		
		
		
	
		
		
	}
}
