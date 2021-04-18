package com.reflection.api.client.dao;

import java.util.Arrays;
import java.util.List;

import com.reflection.api.client.model.Product;

public class ProductDAOMock implements ProductDAO {

	private static final List<Product> PRODUCT_LIST = Arrays.asList(
			new Product("Product 1", 20.0, "Branch 1"),
			new Product("Product 2", 30.0, "Branch 1"),
			new Product("Product 3", 40.0, "Branch 2"));
	
	public List<Product> list() {
		return PRODUCT_LIST;
	}
	
	public Product getProduct(Integer id) {
		return PRODUCT_LIST.get(id);
	}

}