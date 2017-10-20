package com.hin.controller.model;

import java.util.Date;

public class SimulationFinished
{
	int id;
	
	String name;
	
	int cost;
	
	long processingTime;
	
	double facilityUtilization;
	
	double resourceUtilization;
	
	long meanRepairTime;
	
	String bottleneckAnalysis;
	
	int completedQuantity;
	
	Date deadline;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(int cost)
	{
		this.cost = cost;
	}

	public long getProcessingTime()
	{
		return processingTime;
	}

	public void setProcessingTime(long processingTime)
	{
		this.processingTime = processingTime;
	}

	public double getFacilityUtilization()
	{
		return facilityUtilization;
	}

	public void setFacilityUtilization(double facilityUtilization)
	{
		this.facilityUtilization = facilityUtilization;
	}

	public double getResourceUtilization()
	{
		return resourceUtilization;
	}

	public void setResourceUtilization(double resourceUtilization)
	{
		this.resourceUtilization = resourceUtilization;
	}

	public long getMeanRepairTime()
	{
		return meanRepairTime;
	}

	public void setMeanRepairTime(long meanRepairTime)
	{
		this.meanRepairTime = meanRepairTime;
	}

	public String getBottleneckAnalysis()
	{
		return bottleneckAnalysis;
	}

	public void setBottleneckAnalysis(String bottleneckAnalysis)
	{
		this.bottleneckAnalysis = bottleneckAnalysis;
	}

	public int getCompletedQuantity()
	{
		return completedQuantity;
	}

	public void setCompletedQuantity(int completedQuantity)
	{
		this.completedQuantity = completedQuantity;
	}

	public Date getDeadline()
	{
		return deadline;
	}

	public void setDeadline(Date deadline)
	{
		this.deadline = deadline;
	}
	
	
	
	
	
	
}
