package com.hin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hin.entity.Atomic;
import com.hin.entity.SumAtomic;

public interface AtomicService {
	List<Atomic> getByServiceName(String serviceName);

	List<Atomic> getByEnterpriseName(String enterpriseName);

	SumAtomic sumPriceAndTime(Map<String, ArrayList<String>> services);
}
