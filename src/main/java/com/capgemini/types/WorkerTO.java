package com.capgemini.types;

import com.capgemini.enums.Position;

public class WorkerTO {

	public Long id;
	public String firstName;
	public String lastName;
	public Position position;
	private BranchTO branch;

	public WorkerTO(Long id, String firstName, String lastName, Position position, BranchTO branch) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Position getPosition() {
		return position;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final void setPosition(Position position) {
		this.position = position;
	}

	public final BranchTO getBranch() {
		return branch;
	}

	public final void setBranch(BranchTO branch) {
		this.branch = branch;
	}

	public static WorkerTOBuilder builder() {
		return new WorkerTOBuilder();
	}

	public static class WorkerTOBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private Position position;
		private BranchTO branch;

		public WorkerTOBuilder() {
			super();

		}

		public WorkerTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public WorkerTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public WorkerTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public WorkerTOBuilder withPosition(Position position) {
			this.position = position;
			return this;
		}

		public WorkerTOBuilder withBranch(BranchTO branch) {
			this.branch = branch;
			return this;
		}

		public WorkerTO build() {
			return new WorkerTO(id, firstName, lastName, position, branch);
		}
	}

}
