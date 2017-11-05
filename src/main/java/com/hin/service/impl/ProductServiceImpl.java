package com.hin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hin.dao.ProductDao;
import com.hin.entity.Product;
import com.hin.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDao productDao;
	
	@Override
	public void addProduct(Map<String, Object> productInfo) {
		String productName = productInfo.get("productName").toString();
		Integer userID = Integer.parseInt(productInfo.get("userID").toString());
		
		Product product = new Product();
		product.setProductName(productName);
		product.setUserID(userID);
		product.setProductDescript(productInfo.get("productDescript").toString());
		product.setInventory(Integer.parseInt(productInfo.get("inventory").toString()));
		product.setPrice(Double.parseDouble(productInfo.get("price").toString()));
		product.setProductContent(productInfo.get("productContent").toString());
	
		Product exsitProduct = productDao.getProduct(productName, userID);
		
		if (exsitProduct == null) {
			productDao.addProduct(product);
		}
	}

	@Override
	public Product getProduct(String productName, Integer userID) {
		return productDao.getProduct(productName, userID);
	}

	@Override
	public List<Product> getProductByNameAndVersion(String productName) {
		return productDao.getProductByNameAndVersion(productName);
		
	}

	@Override
	public List<Product> getProductByName(String productName) {
		return productDao.getProductByName(productName);
	}

}
