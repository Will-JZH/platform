package com.hin.dao;

import java.util.List;

import com.hin.entity.Atomic;
import com.hin.entity.SumAtomic;

public interface AtomicDao {
	List<Atomic> getByServiceName(String serviceName);
	
	List<Atomic> getByEnterpriseName(String enterpriseName);
	
	SumAtomic sumPriceAndTime(List<String> services);
}
