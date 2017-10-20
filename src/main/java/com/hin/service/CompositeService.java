package com.hin.service;

import java.util.List;
import java.util.Map;

import com.hin.entity.Composite;

public interface CompositeService {
	List<Composite> getAllComposite();

	void addComposite(List<Map<String, Object>> compositeInfo);
}
