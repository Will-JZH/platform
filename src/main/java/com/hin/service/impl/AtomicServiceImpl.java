package com.hin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hin.dao.AtomicDao;
import com.hin.entity.Atomic;
import com.hin.entity.SumAtomic;
import com.hin.service.AtomicService;

@Service("atomicService")
public class AtomicServiceImpl implements AtomicService {
	
	@Resource
	private AtomicDao atomicDao;
	
	public List<Atomic> getByServiceName(String serviceName) {
		return atomicDao.getByServiceName(serviceName);
	}

	public List<Atomic> getByEnterpriseName(String enterpriseName) {
		return atomicDao.getByEnterpriseName(enterpriseName);
	}

	public SumAtomic sumPriceAndTime(Map<String, ArrayList<String>> services) {
		
		return atomicDao.sumPriceAndTime(services.get("services"));
	}

	
	
}
