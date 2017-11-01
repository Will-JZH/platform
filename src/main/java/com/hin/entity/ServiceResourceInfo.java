package com.hin.entity;

public class ServiceResourceInfo {
	private Integer resourceID;
	private String resourceName;
	private Integer userID;
	private Integer category;
	private String description;
	private Double procTime;
	private Double fee;
	private String resourceContent;
	
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getProcTime() {
		return procTime;
	}
	public void setProcTime(Double procTime) {
		this.procTime = procTime;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getResourceContent() {
		return resourceContent;
	}
	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}
}
