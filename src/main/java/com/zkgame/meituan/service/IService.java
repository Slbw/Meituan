package com.zkgame.meituan.service;

import java.util.List;
import java.util.Map;

/**
 * @author Slbw
 *
 */
public interface IService<E, PK> {
	public int removeById(PK id);

	public int save(E record);

	public int saveSelective(E record);

	public E searchById(PK id);

	public int modifyByIdSelective(E record);

	public int modifyById(E record);
	
	public List<E> queryAll();
	
	public List<E> searchByParams(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param conditionMap
	 */
	public void query(Pagination<E> page, Map<String, Object> conditionMap);

	/**
	 * 分页查询
	 * 
	 * @param page
	 */
	public void query(Pagination<E> page);
}

