package com.zkgame.meituan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.ShopMapper;
import com.zkgame.meituan.model.Shop;
import com.zkgame.meituan.service.IShopService;

@Service("ShopService")
public class ShopServiceImpl extends ServiceImpl<Shop, Integer> implements
		IShopService {

	@Autowired
	protected ShopMapper ShopMapper;

	@Override
	public int insertOrUpdateShop(Shop shop) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shopPoiid", shop.getShopPoiid());
		List<Shop> shops = ShopMapper.selectByParams(params);
		if (shops.size() == 0)// 插入新店
		{
			return ShopMapper.insert(shop);
		} else// 更新老店
		{
			if (shops.get(0).getShopVersion() == shop.getShopVersion())
				return 0;//同一版本则不提供更新
			return ShopMapper.updateByShopPoiid(shop);
		}
	}

}