package com.reflection.api.client.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.reflection.api.client.dao.ProductDAO;
import com.reflection.api.client.model.Product;

public class ProductController {
	
	private ProductDAO productDAO;

	public ProductController(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public List<Product> list() {
		return productDAO.list();
	}
	
	public List<Product> filter(String name) {
		return productDAO.list().stream()
				.filter(product -> product.getName().toLowerCase().startsWith(name.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	public List<Product> filter(String name, String brand) {
		return productDAO.list().stream()
				.filter(product -> product.getName().toLowerCase().startsWith(name.toLowerCase())
						&& product.getBrand().equalsIgnoreCase(brand))
				.collect(Collectors.toList());
	}

}