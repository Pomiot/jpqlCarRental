package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BranchDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.enums.Color;
import com.capgemini.enums.Position;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.WorkerTO;
import com.capgemini.types.WorkerTO.WorkerTOBuilder;

import model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
public class CarServiceTest {

	@Autowired
	private CarService carService;

	@Autowired
	private WorkerService workerService;

	@Autowired
	public BranchService branchService;

	@Autowired
	private CarDao carDao;

	@Autowired
	private WorkerDao workerDao;

	@Autowired
	public BranchDao branchDao;

	@Autowired
	public ClientDao clientDao;

	@Autowired
	public RentalDao rentalDao;

	/*
	 * CarTO addedCar; CarTO car;
	 * 
	 * @Before public void addCar(){ car = new
	 * CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).
	 * withEngineSize(321L)
	 * .withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(
	 * CarType.HATCHBACK).build(); addedCar = carService.addCar(car); }
	 */

	@Test
	public void shouldAddCar() {
		// given

		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();

		// when
		CarTO addedCar = carService.addCar(car);
		CarTO selectedCar = carService.findCarById(addedCar.getId());
		// then

		assertNotNull(selectedCar);
		assertEquals(addedCar.getBrand(), selectedCar.getBrand());
		assertEquals(addedCar.getId(), selectedCar.getId());

	}

	@Transactional
	@Test
	public void shouldFindCarByType() {

		// given

		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();
		CarTO addedCar = carService.addCar(car);

		// when

		List<CarTO> selectedCars = carService.findCarByType(CarType.HATCHBACK);
		//List<CarEntity> selectedCars = carDao.findCar(CarBrand.AUDI);

		// then
		assertNotNull(selectedCars);
		assertTrue(selectedCars.stream().anyMatch(b -> b.getBrand().equals(addedCar.getBrand())));
		assertEquals(addedCar.getId(), selectedCars.get(0).getId());

	}

	@Transactional
	@Test
	public void shouldFindCarByTypeAndBrand() {

		// given
		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();
		CarTO addedCar = carService.addCar(car);

		// when

		List<CarTO> selectedCars = carService.findCarByTypeAndBrand(CarType.HATCHBACK, CarBrand.AUDI);

		// then
		assertNotNull(selectedCars);
		assertTrue(selectedCars.stream().anyMatch(b -> b.getType().equals(addedCar.getType())));
		assertTrue(selectedCars.stream().anyMatch(b -> b.getBrand().equals(addedCar.getBrand())));

	}

	@Transactional
	@Test
	public void shouldRemoveCar() {
		// given
		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();
		CarTO addedCar = carService.addCar(car);

		// when
		carService.removeCar(addedCar.getId());
		List<CarTO> selectedCars = carService.findCarByType(CarType.HATCHBACK);

		// then
		assertEquals(0, selectedCars.size());

	}

	@Transactional
	@Test
	public void shouldChangeInformationAboutCar() {
		// given
		CarTO carTO = new CarTOBuilder().withBrand(CarBrand.MERCEDES).withColor(Color.BLUE).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();
		CarTO addedCar = carService.addCar(carTO);

		// when
		CarTO selectedCar = carService.findCarById(addedCar.getId());
		selectedCar.setBrand(CarBrand.HYUNDAI);
		carService.changeInformation(selectedCar);

		// then
		assertEquals(CarBrand.HYUNDAI, carService.findCarById(addedCar.getId()).getBrand());

	}

	@Transactional
	@Test
	public void shouldAddWorkerToCar() {
		// given
		CarTO car = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();

		WorkerTO worker = new WorkerTOBuilder().withFirstName("Jan").withLastName("Kowalski")
				.withPosition(Position.SALESMAN).build();

		CarTO addedCar = carService.addCar(car);
		WorkerTO addedWorker = workerService.addWorker(worker);

		// when
		carService.assignWorker(addedCar.getId(), addedWorker.getId());

		// then
		Set<WorkerEntity> workers = carDao.findOne(addedCar.getId()).getWorkers();
		Set<CarEntity> cars = workerDao.findOne(addedWorker.getId()).getCars();

		assertEquals(1, workers.size());
		assertEquals(1, cars.size());
		assertTrue(cars.contains(carDao.findOne(addedCar.getId())));
		assertTrue(workers.contains(workerDao.findOne(addedWorker.getId())));
	}

	@Transactional
	@Test
	public void shouldFindCarByWorker() {

		// given
		CarTO car1 = new CarTOBuilder().withBrand(CarBrand.AUDI).withColor(Color.BLACK).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.HATCHBACK).build();
		CarTO car2 = new CarTOBuilder().withBrand(CarBrand.HYUNDAI).withColor(Color.RED).withEngineSize(321L)
				.withMAnufactureDate(2018).withMileage(20000L).withPower(222L).withType(CarType.COUPE).build();

		WorkerTO worker = new WorkerTOBuilder().withFirstName("Jan").withLastName("Kowalski")
				.withPosition(Position.SALESMAN).build();

		CarTO addedCar1 = carService.addCar(car1);
		CarTO addedCar2 = carService.addCar(car2);
		WorkerTO addedWorker = workerService.addWorker(worker);

		carService.assignWorker(addedCar1.getId(), addedWorker.getId());
		carService.assignWorker(addedCar2.getId(), addedWorker.getId());

		// when
		List<CarTO> selectedCars = carService.findByWorker(addedWorker.getId());

		// then
		assertNotNull(selectedCars);
		assertEquals(2, selectedCars.size());
		assertEquals(CarBrand.AUDI, selectedCars.get(0).getBrand());
		assertEquals(CarBrand.HYUNDAI, selectedCars.get(1).getBrand());

	}

	@Test
	public void shouldAddCarBranchRentalAndClient() {

		// given
		BranchEntity branch = new BranchEntity("branch@gmail.com", "159263487",
				new Address("Poznan", "Kolorowa", "1459"));
		BranchEntity addedBranch = branchDao.save(branch);

		ClientEntity client = new ClientEntity("John", "Snow");
		ClientEntity addedClient = clientDao.save(client);

		CarEntity car = new CarEntity(CarType.SUV, CarBrand.FORD, 2015, Color.BLACK);
		CarEntity addedCar = carDao.save(car);

		RentalEntity rental = new RentalEntity(new Date(), new Date(), 500L, addedBranch, addedBranch, addedClient,
				addedCar);
		RentalEntity addedRental = rentalDao.save(rental);

		// when
		addedClient.getRentals().add(addedRental);
		addedCar.getRentals().add(addedRental);
		addedBranch.getRentals().add(addedRental);
		addedBranch.getReturns().add(addedRental);

		// then
		assertEquals(addedBranch, branchDao.findOne(addedBranch.getId()));
		assertEquals(addedClient, clientDao.findOne(addedClient.getId()));
		assertEquals(addedCar, carDao.findOne(addedCar.getId()));
		assertEquals(addedRental, rentalDao.findOne(addedRental.getId()));

		Set<RentalEntity> rentalSet = carDao.findOne(addedCar.getId()).getRentals();
		assertEquals(1, rentalSet.size());
		assertEquals(1, addedClient.getRentals().size());

	}

	@Test
	public void shouldRemoveCarWithAllRentalsButNotWithClients() {
		// given
		BranchEntity branch = new BranchEntity("branch@gmail.com", "159263487",
				new Address("Poznan", "Kolorowa", "1459"));
		BranchEntity addedBranch = branchDao.save(branch);

		ClientEntity client = new ClientEntity("John", "Snow");
		ClientEntity addedClient = clientDao.save(client);

		CarEntity car = new CarEntity(CarType.SUV, CarBrand.FORD, 2015, Color.BLACK);
		CarEntity addedCar = carDao.save(car);

		RentalEntity rental = new RentalEntity(new Date(), new Date(), 500L, addedBranch, addedBranch, addedClient,
				addedCar);
		RentalEntity addedRental = rentalDao.save(rental);

		addedClient.getRentals().add(addedRental);
		addedCar.getRentals().add(addedRental);
		addedBranch.getRentals().add(addedRental);
		addedBranch.getReturns().add(addedRental);

		// when
		carDao.delete(addedCar.getId());

		// then
		List<CarEntity> selectedCars = carDao.findCarsByType(CarType.SUV);

		assertEquals(0, selectedCars.size());
		assertNull(rentalDao.findOne(addedRental.getId()));
		assertEquals(addedClient, clientDao.findOne(addedClient.getId()));
		assertEquals(addedBranch, branchDao.findOne(addedBranch.getId()));

	}
	
	@Test
	public void shouldFindCarsRentedByMoreThan10DifferentClients(){
		
		CarEntity car = new CarEntity(CarType.SUV, CarBrand.FORD, 2015, Color.BLACK);
		CarEntity addedCar = carDao.save(car);
		
		for(int i = 0; i <11; i++){
			
			BranchEntity branch = new BranchEntity("branch@gmail.com", "159263487",
					new Address("Poznan", "Kolorowa", "1459"));
			BranchEntity addedBranch = branchDao.save(branch);

			ClientEntity client = new ClientEntity("John", "Snow");
			ClientEntity addedClient = clientDao.save(client);

			RentalEntity rental = new RentalEntity(new Date(), new Date(), 500L, addedBranch, addedBranch, addedClient,
					addedCar);
			RentalEntity addedRental = rentalDao.save(rental);

			addedClient.getRentals().add(addedRental);
			addedCar.getRentals().add(addedRental);
			addedBranch.getRentals().add(addedRental);
			addedBranch.getReturns().add(addedRental);
		}
	
		List<CarEntity> result = carDao.findCarsRentedByMoreThan10DifferentClients();
		
		assertEquals(addedCar, result.get(0));
	}
	
	
}
