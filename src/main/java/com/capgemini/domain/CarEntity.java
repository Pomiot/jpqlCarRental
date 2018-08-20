package com.capgemini.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.enums.Color;

@Entity
@Table(name = "Car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CarType type;

	@Enumerated(EnumType.STRING)
	private CarBrand brand;

	private int manufactureYear;

	@Enumerated(EnumType.STRING)
	private Color color;

	private Long engineSize;
	private Long power;
	private Long mileage;

	@ManyToMany
	private Set<WorkerEntity> workers = new HashSet<>();

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "car")
	private Set<RentalEntity> rentals = new HashSet<>();

	public CarEntity() {

	}

	public CarEntity(CarType type, CarBrand brand, int manufactureYear, Color color) {
		super();
		this.type = type;
		this.brand = brand;
		this.manufactureYear = manufactureYear;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Long getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(long engineSize) {
		this.engineSize = engineSize;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(long power) {
		this.power = power;
	}

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public Set<WorkerEntity> getWorkers() {
		return workers;
	}

	public void setWorkers(Set<WorkerEntity> workers) {
		this.workers = workers;
	}

	public Set<RentalEntity> getRentals() {
		return rentals;
	}

	public void setRentals(Set<RentalEntity> rentals) {
		this.rentals = rentals;
	}

}
