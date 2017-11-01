package com.hin.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hin.dao.ServiceResourceInfoDao;
import com.hin.entity.ServiceResourceInfo;
import com.hin.service.ResourceInfoService;

@Service("resourceInfoService")
public class ResourceInfoServiceImpl implements ResourceInfoService {

	@Resource
	private ServiceResourceInfoDao resourceInfoDao;
	
	@Override
	public void addServiceResource(Map<String, Object> resourceInfo) {
		ServiceResourceInfo resource = new ServiceResourceInfo();
		resource.setResourceName(resourceInfo.get("resourceName").toString());
		resource.setUserID(Integer.parseInt(resourceInfo.get("userID").toString()));
		resource.setCategory(Integer.parseInt(resourceInfo.get("category").toString()));
		resource.setDescription(resourceInfo.get("description").toString());
		resource.setProcTime(Double.parseDouble(resourceInfo.get("procTime").toString()));
		resource.setFee(Double.parseDouble(resourceInfo.get("fee").toString()));
		resource.setResourceContent(resourceInfo.get("resourceContent").toString());
		
		resourceInfoDao.addServiceResource(resource);
	}

	@Override
	public ServiceResourceInfo getServiceResource(String resourceName, Integer userID) {
		return resourceInfoDao.getServiceResource(resourceName, userID);
	}

}
