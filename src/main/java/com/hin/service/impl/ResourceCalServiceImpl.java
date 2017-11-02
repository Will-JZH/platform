package com.hin.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hin.dao.ResourceCalDao;
import com.hin.dao.ServiceResourceInfoDao;
import com.hin.entity.ServiceResourceCal;
import com.hin.entity.ServiceResourceInfo;
import com.hin.service.ResourceCalService;

@Service("resourceCalService")
public class ResourceCalServiceImpl implements ResourceCalService {

	@Resource
	private ResourceCalDao resourceCalDao;
	
	@Resource
	private ServiceResourceInfoDao resourceInfoDao;

	@Override
	public void addResourceCal(Map<String, Object> resourceCalInfo) {
		ServiceResourceCal serviceResourceCal = new ServiceResourceCal();
		String startTime = resourceCalInfo.get("startTime").toString();
		String endTime = resourceCalInfo.get("endTime").toString();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
        java.util.Date start = null;  
        java.util.Date end = null;
        try {  
            start = format.parse(startTime);  
            end = format.parse(endTime);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        Date startDate = new Date(start.getTime());  
        Date endDate = new Date(end.getTime());  
		serviceResourceCal.setStartTime(startDate);
		serviceResourceCal.setEndTime(endDate);
        
		String resourceName = resourceCalInfo.get("resourceName").toString();
		Integer userID = Integer.parseInt(resourceCalInfo.get("userID").toString());
		
		ServiceResourceInfo serviceResourceInfo = resourceInfoDao.getServiceResource(resourceName, userID);
		serviceResourceCal.setResourceID(serviceResourceInfo.getResourceID());
		
		resourceCalDao.addResourceCal(serviceResourceCal);
	}
}
