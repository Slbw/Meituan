package com.zkgame.meituan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.CityMapper;
import com.zkgame.meituan.model.City;
import com.zkgame.meituan.service.ICityService;

@Service("CityService")
public class CityServiceImpl extends ServiceImpl<City, Integer> implements ICityService {

	@Autowired
	protected CityMapper CityMapper;

	@Override
	public int resetTable() {
		
		return CityMapper.resetTable();
	}
}