package com.zkgame.meituan.model;

import java.util.Date;
import java.util.List;

public class Deal extends BaseBean {
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 
	 */
	private String website;
	
	/**
	 * 
	 */
	private String dealMoreImg;
	
	/**
	 * 
	 */
	private Integer reservation;
	
	/**
	 * 
	 */
	private String destination;
	
	/**
	 * 
	 */
	private Integer partner;
	
	/**
	 * 
	 */
	private String cityName;
	
	/**
	 * 
	 */
	private String cityId;
	
	/**
	 * 
	 */
	private String cityUrl;
	
	/**
	 * 
	 */
	private Integer dealId;
	
	/**
	 * 
	 */
	private String dealTitle;
	
	/**
	 * 
	 */
	private Integer dealRank;
	
	/**
	 * 
	 */
	private String dealUrl;
	
	/**
	 * 
	 */
	private String dealImg;
	
	/**
	 * 
	 */
	private Integer dealCateId;
	
	/**
	 * 
	 */
	private String dealCate;
	
	/**
	 * 
	 */
	private Integer dealSubcateId;
	
	/**
	 * 
	 */
	private String dealSubcate;
	
	/**
	 * 
	 */
	private String dealDesc;
	
	/**
	 * 
	 */
	private String value;
	
	/**
	 * 
	 */
	private String price;
	
	/**
	 * 
	 */
	private String rebate;
	
	/**
	 * 
	 */
	private Integer refund;
	
	/**
	 * 
	 */
	private Integer salesMin;
	
	/**
	 * 
	 */
	private Integer salesNum;
	
	/**
	 * 
	 */
	private String soldOut;
	
	/**
	 * 
	 */
	private String isPost;
	
	/**
	 * 
	 */
	private Integer startTime;
	
	/**
	 * 
	 */
	private Integer endTime;
	
	/**
	 * 
	 */
	private Integer couponStartTime;
	
	/**
	 * 
	 */
	private Integer couponEndTime;
	
	/**
	 * 
	 */
	private String dealTips;
	
	/**
	 * 
	 */
	private String dealWow;
	
	/**
	 * 
	 */
	private String dealRange;
	
	/**
	 * 
	 */
	private String dealRangeId;
	
	/**
	 * 
	 */
	private String dealDistrictId;
	
	/**
	 * 
	 */
	private String dealDistrictName;
	
	/**
	 * 
	 */
	private String dealAddress;
	
	/**
	 * 
	 */
	private String dealLng;
	
	/**
	 * 
	 */
	private String dealLat;
	
	/**
	 * 
	 */
	private String dealName;
	
	/**
	 * 
	 */
	private String dealSeller;
	
	/**
	 * 
	 */
	private String dealPhones;
	
	/**
	 * 
	 */
	private String dealRoomtype;
	
	/**
	 * 
	 */
	private String dealRoomtime;
	
	/**
	 * 
	 */
	private Integer coupon;
	
	/**
	 * 
	 */
	private String shops;
	
	/**
	 * 商店列表
	 */
	private List<Shop> shopList;
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId () {
		return id;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getWebsite () {
		return website;
	}
	
	public void setDealMoreImg(String dealMoreImg) {
		this.dealMoreImg = dealMoreImg;
	}
	
	public String getDealMoreImg () {
		return dealMoreImg;
	}
	
	public void setReservation(Integer reservation) {
		this.reservation = reservation;
	}
	
	public Integer getReservation () {
		return reservation;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getDestination () {
		return destination;
	}
	
	public void setPartner(Integer partner) {
		this.partner = partner;
	}
	
	public Integer getPartner () {
		return partner;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCityName () {
		return cityName;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityId () {
		return cityId;
	}
	
	public void setCityUrl(String cityUrl) {
		this.cityUrl = cityUrl;
	}
	
	public String getCityUrl () {
		return cityUrl;
	}
	
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	
	public Integer getDealId () {
		return dealId;
	}
	
	public void setDealTitle(String dealTitle) {
		this.dealTitle = dealTitle;
	}
	
	public String getDealTitle () {
		return dealTitle;
	}
	
	public void setDealRank(Integer dealRank) {
		this.dealRank = dealRank;
	}
	
	public Integer getDealRank () {
		return dealRank;
	}
	
	public void setDealUrl(String dealUrl) {
		this.dealUrl = dealUrl;
	}
	
	public String getDealUrl () {
		return dealUrl;
	}
	
	public void setDealImg(String dealImg) {
		this.dealImg = dealImg;
	}
	
	public String getDealImg () {
		return dealImg;
	}
	
	public void setDealCateId(Integer dealCateId) {
		this.dealCateId = dealCateId;
	}
	
	public Integer getDealCateId () {
		return dealCateId;
	}
	
	public void setDealCate(String dealCate) {
		this.dealCate = dealCate;
	}
	
	public String getDealCate () {
		return dealCate;
	}
	
	public void setDealSubcateId(Integer dealSubcateId) {
		this.dealSubcateId = dealSubcateId;
	}
	
	public Integer getDealSubcateId () {
		return dealSubcateId;
	}
	
	public void setDealSubcate(String dealSubcate) {
		this.dealSubcate = dealSubcate;
	}
	
	public String getDealSubcate () {
		return dealSubcate;
	}
	
	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}
	
	public String getDealDesc () {
		return dealDesc;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue () {
		return value;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPrice () {
		return price;
	}
	
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
	public String getRebate () {
		return rebate;
	}
	
	public void setRefund(Integer refund) {
		this.refund = refund;
	}
	
	public Integer getRefund () {
		return refund;
	}
	
	public void setSalesMin(Integer salesMin) {
		this.salesMin = salesMin;
	}
	
	public Integer getSalesMin () {
		return salesMin;
	}
	
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}
	
	public Integer getSalesNum () {
		return salesNum;
	}
	
	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}
	
	public String getSoldOut () {
		return soldOut;
	}
	
	public void setIsPost(String isPost) {
		this.isPost = isPost;
	}
	
	public String getIsPost () {
		return isPost;
	}
	
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	
	public Integer getStartTime () {
		return startTime;
	}
	
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	
	public Integer getEndTime () {
		return endTime;
	}
	
	public void setCouponStartTime(Integer couponStartTime) {
		this.couponStartTime = couponStartTime;
	}
	
	public Integer getCouponStartTime () {
		return couponStartTime;
	}
	
	public void setCouponEndTime(Integer couponEndTime) {
		this.couponEndTime = couponEndTime;
	}
	
	public Integer getCouponEndTime () {
		return couponEndTime;
	}
	
	public void setDealTips(String dealTips) {
		this.dealTips = dealTips;
	}
	
	public String getDealTips () {
		return dealTips;
	}
	
	public void setDealWow(String dealWow) {
		this.dealWow = dealWow;
	}
	
	public String getDealWow () {
		return dealWow;
	}
	
	public void setDealRange(String dealRange) {
		this.dealRange = dealRange;
	}
	
	public String getDealRange () {
		return dealRange;
	}
	
	public void setDealRangeId(String dealRangeId) {
		this.dealRangeId = dealRangeId;
	}
	
	public String getDealRangeId () {
		return dealRangeId;
	}
	
	public void setDealDistrictId(String dealDistrictId) {
		this.dealDistrictId = dealDistrictId;
	}
	
	public String getDealDistrictId () {
		return dealDistrictId;
	}
	
	public void setDealDistrictName(String dealDistrictName) {
		this.dealDistrictName = dealDistrictName;
	}
	
	public String getDealDistrictName () {
		return dealDistrictName;
	}
	
	public void setDealAddress(String dealAddress) {
		this.dealAddress = dealAddress;
	}
	
	public String getDealAddress () {
		return dealAddress;
	}
	
	public void setDealLng(String dealLng) {
		this.dealLng = dealLng;
	}
	
	public String getDealLng () {
		return dealLng;
	}
	
	public void setDealLat(String dealLat) {
		this.dealLat = dealLat;
	}
	
	public String getDealLat () {
		return dealLat;
	}
	
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	
	public String getDealName () {
		return dealName;
	}
	
	public void setDealSeller(String dealSeller) {
		this.dealSeller = dealSeller;
	}
	
	public String getDealSeller () {
		return dealSeller;
	}
	
	public void setDealPhones(String dealPhones) {
		this.dealPhones = dealPhones;
	}
	
	public String getDealPhones () {
		return dealPhones;
	}
	
	public void setDealRoomtype(String dealRoomtype) {
		this.dealRoomtype = dealRoomtype;
	}
	
	public String getDealRoomtype () {
		return dealRoomtype;
	}
	
	public void setDealRoomtime(String dealRoomtime) {
		this.dealRoomtime = dealRoomtime;
	}
	
	public String getDealRoomtime () {
		return dealRoomtime;
	}
	
	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}
	
	public Integer getCoupon () {
		return coupon;
	}
	
	public void setShops(String shops) {
		this.shops = shops;
	}
	
	public String getShops () {
		return shops;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}