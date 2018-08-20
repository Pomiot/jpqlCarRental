package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BranchTO;
import com.capgemini.types.WorkerTO;

public interface BranchService {

	BranchTO addBranch(BranchTO branch);

	void removeBranch(Long branchId);

	BranchTO changeInformation(BranchTO branch);

	void addWorkerToBranch(Long workerId, Long branchId);
	
	void removeWorkerFormBranch(Long workerId, Long branchId);
	
	BranchTO findBranchById(Long branchId);

	List<WorkerTO> findAllWorkersByBranch(Long branchId);

	List<WorkerTO> findAllWorkersByBranchAndByCar(Long branchId, Long carId);

}
