package com.hin.service;

import java.util.List;

import com.hin.entity.ToSimulated;

public interface ToSimulatedService {
	
	void addToSimulated(String serviceName);

	List<ToSimulated> getToSimulatedInfo();

	void deleteToSimulated(String serviceName);
}
