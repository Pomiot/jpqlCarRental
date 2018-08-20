package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.WorkerEntity;

public interface BranchDao extends Dao<BranchEntity, Long> {

	List<WorkerEntity> findAllWorkersByBranch(Long branchId);

}
