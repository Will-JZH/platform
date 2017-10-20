package com.hin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hin.dao.CompositeDao;
import com.hin.entity.Composite;
import com.hin.service.CompositeService;

@Service("compositeService")
public class CompositeServiceImpl implements CompositeService {
	
	@Autowired
	private CompositeDao compositeDao;
	
	public List<Composite> getAllComposite() {
		return compositeDao.getAllComposite();
	}

	public void addComposite(List<Map<String, Object>> compositeInfo) {
		Composite comp = new Composite();
		comp.setId((Integer) compositeInfo.get(0).get("id"));
		comp.setServiceName((String) compositeInfo.get(1).get("serviceName"));
		comp.setServiceContent((String) compositeInfo.get(2).get("serviceContent"));
		comp.setTotalPrice((Double) compositeInfo.get(3).get("totalPrice"));
		comp.setTotalProcTime((Double) compositeInfo.get(4).get("totalProcTime"));
		
		compositeDao.addComposite(comp);
	}

}
