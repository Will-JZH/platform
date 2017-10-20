package com.hin.entity;

public class Composite {
	private Integer id;
	
	private String serviceName;
	
	private String serviceContent;
	
	private Double totalPrice;
	
	private Double totalProcTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalProcTime() {
		return totalProcTime;
	}

	public void setTotalProcTime(Double totalProcTime) {
		this.totalProcTime = totalProcTime;
	}
}
