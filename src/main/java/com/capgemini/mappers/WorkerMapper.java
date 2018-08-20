package com.capgemini.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.WorkerEntity;
import com.capgemini.types.WorkerTO;
import com.capgemini.types.WorkerTO.WorkerTOBuilder;

public class WorkerMapper {

	public static WorkerTO toWorkerTO(WorkerEntity workerEntity) {
		if (workerEntity == null) {
			return null;
		}
		return new WorkerTOBuilder().withId(workerEntity.getId()).withFirstName(workerEntity.getFirstName())
				.withLastName(workerEntity.getLastName()).withPosition(workerEntity.getPosition())
				.build();
	}

	public static WorkerEntity toWorkerEntity(WorkerTO workerTO) {
		if (workerTO == null) {
			return null;
		}
		WorkerEntity workerEntity = new WorkerEntity();

		workerEntity.setId(workerTO.getId());
		workerEntity.setFirstName(workerTO.getFirstName());
		workerEntity.setLastName(workerTO.getLastName());
		workerEntity.setPosition(workerEntity.getPosition());
		workerEntity.setBranch(workerEntity.getBranch());

		return workerEntity;
	}

	public static Set<WorkerTO> map2TOs(Set<WorkerEntity> workerEntities) {
		return workerEntities.stream().map(WorkerMapper::toWorkerTO).collect(Collectors.toSet());
	}

	public static List<WorkerTO> mapList2TOs(List<WorkerEntity> workerEntities) {
		return workerEntities.stream().map(WorkerMapper::toWorkerTO).collect(Collectors.toList());
	}

	public static Set<WorkerEntity> map2Entities(Set<WorkerTO> workerTOs) {
		return workerTOs.stream().map(WorkerMapper::toWorkerEntity).collect(Collectors.toSet());

	}

}
