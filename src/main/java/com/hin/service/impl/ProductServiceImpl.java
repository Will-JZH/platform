package com.hin.service.impl;

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
		Product product = new Product();
		product.setProductName(productInfo.get("productName").toString());
		product.setUserID(Integer.parseInt(productInfo.get("userID").toString()));
		product.setProductDescript(productInfo.get("productDescript").toString());
		product.setInventory(Integer.parseInt(productInfo.get("inventory").toString()));
		product.setPrice(Double.parseDouble(productInfo.get("price").toString()));
		product.setProductContent(productInfo.get("productContent").toString());
		
		productDao.addProduct(product);
	}

}
