package com.zkgame.meituan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zkgame.meituan.model.Deal;

public interface IDealService extends IService<Deal, Integer> {
	/**
	 * 以城市删除数据
	 * @param map
	 * @return
	 */
	public int removeByCityId(String id);
	
	public List<Deal> searchDeal(Map<String, Object> map);
	
	public int getListCount(Map<String, Object> map);
	
	public List<Map<String, Object>> getCateList(Map<String, Object> map);

}