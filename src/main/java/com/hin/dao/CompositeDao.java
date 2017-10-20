package com.hin.dao;

import java.util.List;

import com.hin.entity.Composite;

public interface CompositeDao {
	List<Composite> getAllComposite();
	
	void addComposite(Composite composite);
}	
