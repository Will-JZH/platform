package com.hin.service;

import java.util.List;
import java.util.Map;

import com.hin.entity.Product;

public interface ProductService {
	void addProduct(Map<String, Object> productInfo);
	Product getProduct(String productName, Integer userID);
	List<Product> getProductByNameAndVersion(String productName);
	List<Product> getProductByName(String productName);
}
