package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BranchDao;
import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.BranchEntity;
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

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
public class BranchServiceTest {

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
	
	
	
	/*@Test
	public void */
	
	@Test
	public void shouldAddBranch(){
		
		//given
		BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
				.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
		
		//when
		BranchTO addedBranch = branchService.addBranch(branch);
		BranchTO selectedBranch = branchService.findBranchById(addedBranch.getId());
		
		//then
		assertNotNull(selectedBranch);
		assertEquals(addedBranch.getId(), selectedBranch.getId());
		assertEquals(addedBranch.getPhoneNumber(), selectedBranch.getPhoneNumber());
	}
	
	@Test
	public void shouldRemoveBranch(){
		
		//given 
		BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
				.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
		BranchTO addedBranch = branchService.addBranch(branch);
		
		//when
		branchService.removeBranch(addedBranch.getId());
		
		//then
		assertNull(branchService.findBranchById(addedBranch.getId()));
	}
	
	@Test 
	public void shouldChangeBranchInformation(){
		//given
		BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
				.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
		BranchTO addedBranch = branchService.addBranch(branch);
		
		//when
		BranchTO selectedBranch = branchService.findBranchById(addedBranch.getId());
		selectedBranch.setPhoneNumber("987654321");
		branchService.changeInformation(selectedBranch);
		
		//then
		assertEquals("987654321", branchService.findBranchById(addedBranch.getId()).getPhoneNumber());
		
	}

		@Test
		public void shouldAddWorkerToBrand(){
			
			//given
			BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
					.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
			WorkerTO worker = new WorkerTOBuilder().withFirstName("Jan").withLastName("Kowalski")
					.withPosition(Position.SALESMAN).build();
			
			BranchTO addedBranch = branchService.addBranch(branch);
			WorkerTO addedWorker = workerService.addWorker(worker);
			
			//when
			branchService.addWorkerToBranch(addedWorker.getId(), addedBranch.getId());
	
			//then
			Set<WorkerEntity> workers = branchDao.findOne(addedBranch.getId()).getWorkers();
			BranchEntity selectedBranch = workerDao.findOne(addedWorker.getId()).getBranch();

			assertEquals(1, workers.size());
			assertNotNull(selectedBranch);
			assertTrue(workers.contains(workerDao.findOne(addedWorker.getId())));
			assertTrue(selectedBranch.equals(branchDao.findOne(addedBranch.getId())));
			
		}
		
		@Test 
		public void shouldRemoveWorkerFormBranch(){
			//given
			BranchTO branch = new BranchTOBuilder().withAddress(new Address("Poznan", "Wiankowa", "1500100900"))
					.withEmail("branch@gmail.com").withPhoneNumber("123456789").build();
			WorkerTO worker = new WorkerTOBuilder().withFirstName("Jan").withLastName("Kowalski")
					.withPosition(Position.SALESMAN).build();
			
			BranchTO addedBranch = branchService.addBranch(branch);
			WorkerTO addedWorker = workerService.addWorker(worker);
			
			branchService.addWorkerToBranch(addedWorker.getId(), addedBranch.getId());
			
			//when
			branchService.removeWorkerFormBranch(addedWorker.getId(), addedBranch.getId());
			
			//then
			Set<WorkerEntity> workers = branchDao.findOne(addedBranch.getId()).getWorkers();
			BranchEntity selectedBranch = workerDao.findOne(addedWorker.getId()).getBranch();
			
			assertEquals(0, workers.size());
			assertNull(selectedBranch);			
		}
		
		@Test 
		public void shouldReturnAllActualWorkersFromBranch(){
			
			//given
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
			
			
			BranchTO addedBranch = branchService.addBranch(branch);
			WorkerTO addedWorker1 = workerService.addWorker(worker1);
			WorkerTO addedWorker2 = workerService.addWorker(worker2);
			WorkerTO addedWorker3 = workerService.addWorker(worker3);
			WorkerTO addedWorker4 = workerService.addWorker(worker4);
			
			branchService.addWorkerToBranch(addedWorker1.getId(), addedBranch.getId());
			branchService.addWorkerToBranch(addedWorker2.getId(), addedBranch.getId());
			branchService.addWorkerToBranch(addedWorker3.getId(), addedBranch.getId());
			branchService.addWorkerToBranch(addedWorker4.getId(), addedBranch.getId());
			
			
			//when
			List<WorkerTO> workers = branchService.findAllWorkersByBranch(addedBranch.getId());
			
			
			//then
			assertNotNull(workers);
			assertEquals(4, workers.size());
		
		}
		
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
}
