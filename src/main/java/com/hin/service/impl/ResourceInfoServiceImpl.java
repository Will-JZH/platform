package com.hin.service.impl;

import java.util.List;
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
		String resourceName = resourceInfo.get("resourceName").toString();
		Integer userID = Integer.parseInt(resourceInfo.get("userID").toString());
		
		ServiceResourceInfo resource = new ServiceResourceInfo();
		resource.setResourceName(resourceName);
		resource.setUserID(userID);
		resource.setCategory(Integer.parseInt(resourceInfo.get("category").toString()));
		resource.setDescription(resourceInfo.get("description").toString());
		resource.setProcTime(Double.parseDouble(resourceInfo.get("procTime").toString()));
		resource.setFee(Double.parseDouble(resourceInfo.get("fee").toString()));
		resource.setResourceContent(resourceInfo.get("resourceContent").toString());
		
		ServiceResourceInfo exsitResource = resourceInfoDao.getServiceResource(resourceName, userID);
		
		if (exsitResource == null) {
			resourceInfoDao.addServiceResource(resource);
		}
	}

	@Override
	public ServiceResourceInfo getServiceResource(String resourceName, Integer userID) {
		return resourceInfoDao.getServiceResource(resourceName, userID);
	}

	@Override
	public List<ServiceResourceInfo> getServiceResourceByName(String resourceName) {
		return resourceInfoDao.getServiceResourceByName(resourceName);
	}

}
