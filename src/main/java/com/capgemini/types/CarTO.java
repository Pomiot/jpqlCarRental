package com.capgemini.types;

import com.capgemini.enums.CarBrand;
import com.capgemini.enums.CarType;
import com.capgemini.enums.Color;

public class CarTO {

	private Long id;
	private CarType type;
	private CarBrand brand;
	private int manufactureYear;
	private Color color;
	private long engineSize;
	private long power;
	private long mileage;

	public CarTO(Long id, CarType type, CarBrand brand, int manufactureYear, Color color, long engineSize, long power,
			long mileage) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.manufactureYear = manufactureYear;
		this.color = color;
		this.engineSize = engineSize;
		this.power = power;
		this.mileage = mileage;

	}

	public Long getId() {
		return id;
	}

	public CarType getType() {
		return type;
	}

	public CarBrand getBrand() {
		return brand;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public Color getColor() {
		return color;
	}

	public long getEngineSize() {
		return engineSize;
	}

	public long getPower() {
		return power;
	}

	public long getMileage() {
		return mileage;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEngineSize(long engineSize) {
		this.engineSize = engineSize;
	}

	public void setPower(long power) {
		this.power = power;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public static CarTOBuilder builder() {
		return new CarTOBuilder();
	}

	public static class CarTOBuilder {

		private Long id;
		private CarType type;
		private CarBrand brand;
		private int manufactureYear;
		private Color color;
		private long engineSize;
		private long power;
		private long mileage;

		public CarTOBuilder() {
			super();
		}

		public CarTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public CarTOBuilder withType(CarType type) {
			this.type = type;
			return this;
		}

		public CarTOBuilder withBrand(CarBrand brand) {
			this.brand = brand;
			return this;
		}

		public CarTOBuilder withMAnufactureDate(int year) {
			this.manufactureYear = year;
			return this;
		}

		public CarTOBuilder withColor(Color color) {
			this.color = color;
			return this;
		}

		public CarTOBuilder withEngineSize(Long engineSize) {
			this.engineSize = engineSize;
			return this;
		}

		public CarTOBuilder withPower(Long power) {
			this.power = power;
			return this;
		}

		public CarTOBuilder withMileage(Long mileage) {
			this.mileage = mileage;
			return this;

		}

		public CarTO build() {

			return new CarTO(id, type, brand, manufactureYear, color, engineSize, power, mileage);

		}
	}

}
