package com.capgemini.types;

import java.util.HashSet;
import java.util.Set;

import model.Address;

public class BranchTO {

	private Long id;
	private String email;
	private String phoneNumber;
	private Address address;
	private Set<WorkerTO> workers;

	public BranchTO(Long id, String email, String phoneNumber, Address address) {
		super();
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPhoneNumber() {
		return phoneNumber;
	}

	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public final Address getAddress() {
		return address;
	}

	public final void setAddress(Address address) {
		this.address = address;
	}

	public final Set<WorkerTO> getWorkers() {
		return workers;
	}

	public final void setWorkers(Set<WorkerTO> workers) {
		this.workers = workers;
	}

	public static BranchTOBuilder builder() {
		return new BranchTOBuilder();
	}

	public static class BranchTOBuilder {
		private Long id;
		private String email;
		private String phoneNumber;
		private Address address;
		private Set<WorkerTO> workers = new HashSet<>();

		public BranchTOBuilder() {

		}

		public BranchTOBuilder withWorker(WorkerTO worker) {
			this.workers.add(worker);
			return this;
		}

		public BranchTOBuilder withWorkers(Set<WorkerTO> workersToAdd) {
			this.workers.addAll(workersToAdd);
			return this;
		}

		public BranchTOBuilder withId(Long id) {
			this.id = id;
			return this;

		}

		public BranchTOBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public BranchTOBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public BranchTOBuilder withAddress(Address address) {
			this.address = address;
			return this;
		}

		public BranchTO build() {
			return new BranchTO(id, email, phoneNumber, address);
		}
	}
}
