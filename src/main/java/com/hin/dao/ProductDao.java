package com.hin.dao;

import com.hin.entity.Product;

public interface ProductDao {
	void addProduct(Product product);
	Product getProduct(String productName, Integer userID);
}
