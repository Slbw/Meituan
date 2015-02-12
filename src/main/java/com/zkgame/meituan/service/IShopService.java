package com.zkgame.meituan.service;

import com.zkgame.meituan.model.Shop;

public interface IShopService extends IService<Shop, Integer> {
	
	/**
	 * 添加或者更新商店
	 * @param shop
	 * @return
	 */
	public int insertOrUpdateShop(Shop shop);

}