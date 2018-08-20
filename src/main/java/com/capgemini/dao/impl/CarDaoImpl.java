package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@Override
	public List<CarEntity> findCarsByType(CarType type) {
		TypedQuery<CarEntity> query = entityManager
				.createQuery("select car from CarEntity car where upper(car.type) like :type)", CarEntity.class);
		query.setParameter("type", type);

		try {

			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<CarEntity> findCarsByTypeAndBrand(CarType type, CarBrand brand) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.type) = :type AND upper(car.brand) = :brand",
				CarEntity.class);
		query.setParameter("type", type).setParameter("brand", brand);
		return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarsByWorker(Long workerId) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car join car.workers w where w.id = :workerId", CarEntity.class);

		query.setParameter("workerId", workerId);
		return query.getResultList();

	}

	public List<CarEntity> findCar(CarBrand brand) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarEntity> cq = cb.createQuery(CarEntity.class);
		Root<CarEntity> root = cq.from(CarEntity.class);
	//		ParameterExpression<CarBrand> p = cb.parameter(CarBrand.class);
		cq.select(root).where(root.get("brand").in(brand));
		TypedQuery<CarEntity> q = entityManager.createQuery(cq);
		// q.setParameter(p, brand);
		return q.getResultList();

	}

	@Override
	public List<CarEntity> findCarsRentedByMoreThan10DifferentClients() {

		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where car in"
						+ "(select r.car from RentalEntity r group by r.car having count(distinct r.client) > 10)",
				CarEntity.class);

		return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarsUsedInTimeInterval() {
		// TODO Auto-generated method stub
		return null;
	}

}
