package com.capgemini.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.BranchDao;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.mappers.BranchMapper;
import com.capgemini.mappers.WorkerMapper;
import com.capgemini.service.BranchService;
import com.capgemini.types.BranchTO;
import com.capgemini.types.WorkerTO;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private WorkerDao workerDao;

	@Autowired
	private CarDao carDao;

	@Override
	public BranchTO addBranch(BranchTO branch) {
		BranchEntity branchEntity = branchDao.save(BranchMapper.toBranchEntity(branch));
		return BranchMapper.toBranchTO(branchEntity);
	}

	@Override
	public void removeBranch(Long branchId) {
		branchDao.delete(branchId);

	}

	@Override
	public BranchTO changeInformation(BranchTO branch) {
		BranchEntity branchEntity = branchDao.update(BranchMapper.toBranchEntity(branch));
		return BranchMapper.toBranchTO(branchEntity);
	}

	@Override
	public void addWorkerToBranch(Long workerId, Long branchId) {
		// add worker to branch
		WorkerEntity worker = workerDao.findOne(workerId);
		branchDao.findOne(branchId).getWorkers().add(worker);

		// add branch to worker
		BranchEntity branch = branchDao.findOne(branchId);
		workerDao.findOne(workerId).setBranch(branch);

		branchDao.update(branch);
		workerDao.update(worker);

	}

	@Override
	public List<WorkerTO> findAllWorkersByBranch(Long branchId) {
		List<WorkerEntity> worksersEntity = branchDao.findAllWorkersByBranch(branchId);
		return WorkerMapper.mapList2TOs(worksersEntity);
	}

	@Override
	public List<WorkerTO> findAllWorkersByBranchAndByCar(Long branchId, Long carId) {
		List<WorkerEntity> workers = branchDao.findAllWorkersByBranch(branchId).stream()
				.filter(c -> c.getCars().contains(carDao.findOne(carId))).collect(Collectors.toList());

		return WorkerMapper.mapList2TOs(workers);
	}

	@Override
	public BranchTO findBranchById(Long branchId) {

		return BranchMapper.toBranchTO(branchDao.findOne(branchId));
	}

	@Override
	public void removeWorkerFormBranch(Long workerId, Long branchId) {

		WorkerEntity worker = workerDao.findOne(workerId);
		branchDao.findOne(branchId).getWorkers().remove(worker);
		workerDao.findOne(workerId).setBranch(null);
	}

}
