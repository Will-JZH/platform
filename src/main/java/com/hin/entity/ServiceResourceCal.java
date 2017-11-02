package com.hin.entity;

import java.sql.Date;

public class ServiceResourceCal {
	Integer calendarID;
	Integer resourceID;
	Date startTime;
	Date endTime;
	
	public Integer getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(Integer calendarID) {
		this.calendarID = calendarID;
	}
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
