package com.zkgame.meituan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.DealMapper;
import com.zkgame.meituan.model.Deal;
import com.zkgame.meituan.service.IDealService;

@Service("DealService")
public class DealServiceImpl extends ServiceImpl<Deal, Integer> implements
		IDealService {

	@Autowired
	protected DealMapper DealMapper;

	@Override
	public int removeByCityId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", id);
		return DealMapper.removeByCityId(map);
	}

	@Override
	public List<Deal> searchDeal(Map<String, Object> map) {
		
		return DealMapper.searchDeal(map);
	}

	@Override
	public int getListCount(Map<String, Object> map) {
		
		return DealMapper.selectCntByParams(map);
	}

	@Override
	public List<Map<String, Object>> getCateList(Map<String, Object> map) {
		
		return DealMapper.getCateList(map);
	}

}