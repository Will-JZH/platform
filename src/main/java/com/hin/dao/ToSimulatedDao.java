package com.hin.dao;

import java.util.List;

import com.hin.entity.ToSimulated;

public interface ToSimulatedDao {
	
	void addToSimulated(String serviceName);
	
	List<ToSimulated> getToSimulatedInfo();
	
	void deleteToSimulated(String serviceName);
}
