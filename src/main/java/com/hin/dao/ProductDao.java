package com.hin.dao;

import java.util.List;

import com.hin.entity.Product;

public interface ProductDao {
	void addProduct(Product product);
	Product getProduct(String productName, Integer userID);
	List<Product> getProductByNameAndVersion(String productName);
	List<Product> getProductByName(String productName);
}
