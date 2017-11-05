package com.hin.dao;

import java.util.List;

import com.hin.entity.ServiceResourceInfo;

public interface ServiceResourceInfoDao {
	void addServiceResource(ServiceResourceInfo serviceResourceInfo);
	
	ServiceResourceInfo getServiceResource(String resourceName, Integer userID);
	
	List<ServiceResourceInfo> getServiceResourceByName(String resourceName);
}
