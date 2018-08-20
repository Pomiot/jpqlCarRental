package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.WorkerEntity;

import model.EmployeeSearchCriteria;

public interface WorkerDao extends Dao <WorkerEntity, Long> {

	List<WorkerEntity> findWorkersByBranchOrCarOrPosition(EmployeeSearchCriteria esc);
}
