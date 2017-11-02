package com.hin.service;

import java.util.Map;

import com.hin.entity.ServiceResourceInfo;

public interface ResourceInfoService {
	void addServiceResource(Map<String, Object> resourceInfo);
	
	ServiceResourceInfo getServiceResource(String resourceName, Integer userID);
}
