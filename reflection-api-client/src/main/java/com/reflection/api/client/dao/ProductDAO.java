package com.reflection.api.client.dao;

import java.util.List;

import com.reflection.api.client.model.Product;

public interface ProductDAO {

	public List<Product> list();
	public Product getProduct(Integer id);

}
