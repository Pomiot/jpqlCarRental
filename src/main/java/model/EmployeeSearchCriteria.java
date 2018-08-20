package model;

import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.enums.Position;

public class EmployeeSearchCriteria {

	private BranchEntity branch;
	private CarEntity car;
	private Position position;
	
	public EmployeeSearchCriteria(){
		
	}
	
	public EmployeeSearchCriteria(BranchEntity branch, CarEntity car, Position position) {
		
		this.branch = branch;
		this.car = car;
		this.position = position;
	}

	public final BranchEntity getBranch() {
		return branch;
	}
	public final void setBranch(BranchEntity branch) {
		this.branch = branch;
	}
	public final CarEntity getCar() {
		return car;
	}
	public final void setCar(CarEntity car) {
		this.car = car;
	}
	public final Position getPosition() {
		return position;
	}
	public final void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
