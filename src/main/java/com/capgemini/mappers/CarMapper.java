package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

public class CarMapper {

	public static CarTO toCarTO(CarEntity carEntity) {
		if (carEntity == null) {
			return null;
		}
		return new CarTOBuilder().withId(carEntity.getId()).withType(carEntity.getType())
				.withBrand(carEntity.getBrand()).withMAnufactureDate(carEntity.getManufactureYear())
				.withColor(carEntity.getColor()).withEngineSize(carEntity.getEngineSize())
				.withPower(carEntity.getPower()).withMileage(carEntity.getMileage()).build();
	}

	public static CarEntity toCarEntity(CarTO carTO) {
		if (carTO == null) {
			return null;
		}
		CarEntity carEntity = new CarEntity();

		carEntity.setId(carTO.getId());
		carEntity.setType(carTO.getType());
		carEntity.setBrand(carTO.getBrand());
		carEntity.setManufactureYear(carTO.getManufactureYear());
		carEntity.setColor(carTO.getColor());
		carEntity.setEngineSize(carTO.getEngineSize());
		carEntity.setPower(carTO.getPower());
		carEntity.setMileage(carTO.getMileage());
		return carEntity;
	}

	public static List<CarTO> map2TOs(List<CarEntity> carEntities) {
		return carEntities.stream().map(CarMapper::toCarTO).collect(Collectors.toList());
	}

	public static List<CarEntity> map2Entities(List<CarTO> carTOs) {
		return carTOs.stream().map(CarMapper::toCarEntity).collect(Collectors.toList());

	}

}
