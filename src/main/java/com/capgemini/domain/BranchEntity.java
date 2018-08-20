package com.capgemini.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.Address;

@Entity
@Table(name = "Branch")
public class BranchEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;
	private String phoneNumber;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "branch")
	private Set<WorkerEntity> workers = new HashSet<>();

	@OneToMany(mappedBy = "rentalBranch")
	private Set<RentalEntity> rentals = new HashSet<>();

	@OneToMany(mappedBy = "returnBranch")
	private Set<RentalEntity> returns = new HashSet<>();

	public BranchEntity() {

	}

	public BranchEntity(String email, String phoneNumber, Address address) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public Set<RentalEntity> getReturns() {
		return returns;
	}

	public void setReturns(Set<RentalEntity> returns) {
		this.returns = returns;
	}

}
