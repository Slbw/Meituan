package com.zkgame.meituan.model;

import java.util.List;

public class MDeal {

	private Deal deal;
	private List<Shop> shops;

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

}
