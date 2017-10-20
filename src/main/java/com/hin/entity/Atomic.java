package com.hin.entity;

public class Atomic {
	private Integer id;
	private String serviceName;
	private Double price;
	private Double procTime;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getProcTime() {
		return procTime;
	}
	public void setProcTime(Double procTime) {
		this.procTime = procTime;
	}
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
}
