package com.zkgame.meituan.model;

import java.util.Date;

public class City extends BaseBean {
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 
	 */
	private String divisionId;
	
	/**
	 * 
	 */
	private String divisionName;
	
	/**
	 * 
	 */
	private String locationTimezone;
	
	/**
	 * 
	 */
	private String locationTimezoneOffsetGmt;
	
	/**
	 * 
	 */
	private String locationLatitude;
	
	/**
	 * 
	 */
	private String locationLongitude;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId () {
		return id;
	}
	
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	
	public String getDivisionId () {
		return divisionId;
	}
	
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	public String getDivisionName () {
		return divisionName;
	}
	
	public void setLocationTimezone(String locationTimezone) {
		this.locationTimezone = locationTimezone;
	}
	
	public String getLocationTimezone () {
		return locationTimezone;
	}
	
	public void setLocationTimezoneOffsetGmt(String locationTimezoneOffsetGmt) {
		this.locationTimezoneOffsetGmt = locationTimezoneOffsetGmt;
	}
	
	public String getLocationTimezoneOffsetGmt () {
		return locationTimezoneOffsetGmt;
	}
	
	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	
	public String getLocationLatitude () {
		return locationLatitude;
	}
	
	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	public String getLocationLongitude () {
		return locationLongitude;
	}
	
}