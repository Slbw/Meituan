package com.zkgame.meituan.dao.maper;

import com.zkgame.meituan.dao.SpringMybatisDao;
import com.zkgame.meituan.model.City;

public interface CityMapper extends SpringMybatisDao<City, Integer> {
	
	public int resetTable();

}