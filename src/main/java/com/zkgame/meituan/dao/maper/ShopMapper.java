package com.zkgame.meituan.dao.maper;

import com.zkgame.meituan.dao.SpringMybatisDao;
import com.zkgame.meituan.model.Shop;

public interface ShopMapper extends SpringMybatisDao<Shop, Integer> {

	
	public int updateByShopPoiid(Shop shop);
	
}