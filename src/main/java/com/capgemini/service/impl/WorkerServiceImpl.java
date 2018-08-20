package com.capgemini.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.mappers.WorkerMapper;
import com.capgemini.service.WorkerService;
import com.capgemini.types.WorkerTO;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao workerDao;

	@Override
	public WorkerTO addWorker(WorkerTO worker) {
		WorkerEntity workerEnitity = workerDao.save(WorkerMapper.toWorkerEntity(worker));
		return WorkerMapper.toWorkerTO(workerEnitity);
	}

	@Override
	public WorkerTO findWorkerById(Long workerId) {
		return WorkerMapper.toWorkerTO(workerDao.findOne(workerId));
	}

}
