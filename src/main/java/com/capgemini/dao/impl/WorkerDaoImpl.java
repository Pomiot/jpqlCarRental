package com.capgemini.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.WorkerDao;
import com.capgemini.domain.BranchEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.WorkerEntity;
import com.capgemini.enums.Position;

import model.EmployeeSearchCriteria;

@Repository
public class WorkerDaoImpl extends AbstractDao<WorkerEntity, Long> implements WorkerDao {

	@Override
	public List<WorkerEntity> findWorkersByBranchOrCarOrPosition(EmployeeSearchCriteria esc) {

		BranchEntity branch = esc.getBranch();
		CarEntity car = esc.getCar();
		Position position = esc.getPosition();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<WorkerEntity> cq = cb.createQuery(WorkerEntity.class);
		Root<WorkerEntity> root = cq.from(WorkerEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (branch != null) {
			predicates.add(cb.equal(root.get("branch"), branch));
		}
		if (car != null) {
			predicates.add(cb.equal(root.get("cars"), car));
		}
		if (position != null) {
			predicates.add(cb.equal(root.get("position"), position));
		}

		cq.select(root).where(predicates.toArray(new Predicate[] {}));

		return entityManager.createQuery(cq).getResultList();

	}
}
