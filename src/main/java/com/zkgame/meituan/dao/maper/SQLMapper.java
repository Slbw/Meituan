package com.zkgame.meituan.dao.maper;

import java.util.List;
import java.util.Map;

public interface SQLMapper {
	public List<Map<String, Object>> selectBySQL(String sql);
}