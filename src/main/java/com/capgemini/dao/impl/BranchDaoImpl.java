package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.BranchDao;
import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.WorkerEntity;

@Repository
public class BranchDaoImpl extends AbstractDao<BranchEntity, Long> implements BranchDao {

	@Override
	public List<WorkerEntity> findAllWorkersByBranch(Long branchId) {
		TypedQuery<WorkerEntity> query = entityManager.createQuery(
				"select worker from WorkerEntity worker inner join worker.branch b where b.id = :branchId",
				WorkerEntity.class);
		query.setParameter("branchId", branchId);

		return query.getResultList();

	}

}
