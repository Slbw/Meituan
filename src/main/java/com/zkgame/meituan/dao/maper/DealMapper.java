package com.zkgame.meituan.dao.maper;

import java.util.List;
import java.util.Map;

import com.zkgame.meituan.dao.SpringMybatisDao;
import com.zkgame.meituan.model.Deal;

public interface DealMapper extends SpringMybatisDao<Deal, Integer> {
	
	public int removeByCityId(Map<String,Object> map);
	
	public List<Deal> searchDeal(Map<String,Object> map);
	
	List<Map<String, Object>> getCateList(Map<String, Object> map);

}