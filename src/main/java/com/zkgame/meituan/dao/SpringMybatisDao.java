package com.zkgame.meituan.dao;

import java.util.List;
import java.util.Map;

public interface SpringMybatisDao<E, PK> {
	int deleteByPrimaryKey(PK id);

	int insert(E record);

	int insertSelective(E record);

	E selectByPrimaryKey(PK id);

	int updateByPrimaryKeySelective(E record);

	int updateByPrimaryKey(E record);
	
	List<E> selectAll();
	
	List<E> selectByParams(Map<String, Object> params);
	
	int selectCntByParams(Map<String, Object> params);
}
