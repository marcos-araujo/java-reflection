package com.reflection.api.client.model;

import com.reflection.api.annotation.NameTagXml;

@NameTagXml("product")
public class Product {

	@NameTagXml("name")
	private String name;
	@NameTagXml("value")
	private double value;
	@NameTagXml("brand")
	private String brand;

	public Product(String name, double value, String brand) {
		this.name = name;
		this.value = value;
		this.brand = brand;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", value=" + value + ", brand=" + brand + "]";
	}

}