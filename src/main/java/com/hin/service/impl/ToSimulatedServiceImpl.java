package com.hin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hin.dao.ToSimulatedDao;
import com.hin.entity.ToSimulated;
import com.hin.service.ToSimulatedService;

public class ToSimulatedServiceImpl implements ToSimulatedService {

	@Autowired
	private ToSimulatedDao toSimulatedDao;
	
	public void addToSimulated(String serviceName) {
		
		toSimulatedDao.addToSimulated(serviceName);
	}

	public List<ToSimulated> getToSimulatedInfo() {
		
		return toSimulatedDao.getToSimulatedInfo();
	}

	public void deleteToSimulated(String serviceName) {
		
		toSimulatedDao.deleteToSimulated(serviceName);
	}
	
}
