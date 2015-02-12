
package com.zkgame.meituan.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.zkgame.meituan.dao.SpringMybatisDao;
import com.zkgame.meituan.service.IService;
import com.zkgame.meituan.service.Pagination;

/**
 * @author Slbw
 * 
 */
public class ServiceImpl<E, PK> implements IService<E, PK> {
	private SpringMybatisDao<E, PK> baseDao;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setBaseDao() {
		try {
			Field[] fields = getClass().getDeclaredFields();
			for (Field f : fields) {
				if (Modifier.PROTECTED == f.getModifiers()) {
					Object obj = f.get(this);
					if (obj instanceof SpringMybatisDao) {
						baseDao = (SpringMybatisDao<E, PK>) obj;
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int removeById(PK id) {
		return baseDao.deleteByPrimaryKey(id);
	}

	@Override
	public int save(E record) {
		return baseDao.insert(record);
	}

	@Override
	public int saveSelective(E record) {
		return baseDao.insertSelective(record);
	}

	@Override
	public E searchById(PK id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public int modifyByIdSelective(E record) {
		return baseDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int modifyById(E record) {
		return baseDao.updateByPrimaryKey(record);
	}

	@Override
	public List<E> queryAll() {
		return baseDao.selectAll();
	}

	@Override
	public List<E> searchByParams(Map<String, Object> params) {
		return baseDao.selectByParams(params);
	}

	@Override
	public void query(Pagination<E> page, Map<String, Object> conditionMap) {
		Map<String, Object> con = conditionMap;
		if (conditionMap == null) {
			con = new HashMap<String, Object>();
		}
		con.put("startRow", page.getFirstResult());
		con.put("pageSize", page.getPageSize());
		page.setItems(baseDao.selectByParams(con));
		page.setTotalCount(baseDao.selectCntByParams(con));
	}

	@Override
	public void query(Pagination<E> page) {
		query(page, null);
	}
}
