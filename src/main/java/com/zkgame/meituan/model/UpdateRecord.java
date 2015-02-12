package com.zkgame.meituan.model;

import java.util.Date;

public class UpdateRecord extends BaseBean {
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 
	 */
	private String cityId;
	
	/**
	 * 
	 */
	private String cityName;
	
	/**
	 * 
	 */
	private Integer dealCount;
	
	/**
	 * 
	 */
	private Integer shopCount;
	
	/**
	 * 
	 */
	private Integer lastUpdate;
	
	/**
	 * 
	 */
	private Integer version;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId () {
		return id;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityId () {
		return cityId;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCityName () {
		return cityName;
	}
	
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	
	public Integer getDealCount () {
		return dealCount;
	}
	
	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}
	
	public Integer getShopCount () {
		return shopCount;
	}
	
	public void setLastUpdate(Integer lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Integer getLastUpdate () {
		return lastUpdate;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion () {
		return version;
	}
	
}