package com.hin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hin.entity.Product;
import com.hin.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public @ResponseBody void addProduct(
    		 @RequestBody Map<String,Object>  params) throws Exception{
		productService.addProduct(params);
    }
	
	@RequestMapping(value = "/getProduct/{productName}/{userID}", method = RequestMethod.GET)
    public @ResponseBody Product getProduct(
    		@PathVariable String productName, @PathVariable Integer userID) throws Exception{
		return productService.getProduct(productName, userID);
    }
	
	@RequestMapping(value = "/getProductByNameAndVersion/{productName}", method = RequestMethod.GET)
    public @ResponseBody List<Product> getProductByNameAndVersion(
    		@PathVariable String productName) throws Exception{
		return productService.getProductByNameAndVersion(productName);
    }
	
	@RequestMapping(value = "/getProductByName/{productName}", method = RequestMethod.GET)
    public @ResponseBody List<Product> getProductByName(
    		@PathVariable String productName) throws Exception{
		return productService.getProductByName(productName);
    }
}
