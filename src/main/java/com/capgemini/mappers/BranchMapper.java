package com.capgemini.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.BranchEntity;
import com.capgemini.types.BranchTO;
import com.capgemini.types.BranchTO.BranchTOBuilder;
import com.capgemini.types.WorkerTO;

public class BranchMapper {

	public static BranchTO toBranchTO(BranchEntity branchEntity) {
		if (branchEntity == null) {
			return null;
		}
		Set<WorkerTO> workersTOs = WorkerMapper.map2TOs(branchEntity.getWorkers());
		return new BranchTOBuilder().withWorkers(workersTOs).withId(branchEntity.getId())
				.withEmail(branchEntity.getEmail()).withPhoneNumber(branchEntity.getPhoneNumber())
				.withAddress(branchEntity.getAddress()).build();
	}

	public static BranchEntity toBranchEntity(BranchTO branchTO) {
		if (branchTO == null) {
			return null;
		}

		BranchEntity branchEntity = new BranchEntity();

		branchEntity.setId(branchTO.getId());
		branchEntity.setEmail(branchTO.getEmail());
		branchEntity.setPhoneNumber(branchTO.getPhoneNumber());
		branchEntity.setAddress(branchTO.getAddress());

		return branchEntity;
	}

	public static List<BranchTO> map2TOs(List<BranchEntity> branchEntities) {
		return branchEntities.stream().map(BranchMapper::toBranchTO).collect(Collectors.toList());
	}

	public static List<BranchEntity> map2Entities(List<BranchTO> branchTOs) {
		return branchTOs.stream().map(BranchMapper::toBranchEntity).collect(Collectors.toList());

	}
}
