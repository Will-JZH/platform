package com.hin.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hin.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public @ResponseBody void addProduct(
    		 @RequestBody Map<String,Object>  params) throws Exception{
		productService.addProduct(params);
    }
	
}
