package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.enums.Position;

@Entity
@Table (name="Worker")
public class WorkerEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private Date birthdate;
	private Position position;
	
	@ManyToMany(mappedBy = "workers")


	private Set<CarEntity> cars = new HashSet<>();
	
	 @ManyToOne
	 private BranchEntity branch; 

	 public WorkerEntity(){
		 
	 }

	public WorkerEntity(String firstName, String lastName, Position position) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	
	public final void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Set<CarEntity> getCars() {
		return cars;
	}
	public void setCars(Set<CarEntity> cars) {
		this.cars = cars;
	}
	public BranchEntity getBranch() {
		return branch;
	}
	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}
	
	

	
	

}
