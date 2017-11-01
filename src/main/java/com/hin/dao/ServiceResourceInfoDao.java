package com.hin.dao;

import com.hin.entity.ServiceResourceInfo;

public interface ServiceResourceInfoDao {
	void addServiceResource(ServiceResourceInfo serviceResourceInfo);
	
	ServiceResourceInfo getServiceResource(String resourceName, Integer userID);
}
