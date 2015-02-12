package com.zkgame.meituan.service;

import java.util.List;

import com.zkgame.meituan.model.CodeDetailBean;

public interface ICodeDetailService {
	public List<CodeDetailBean> getCodeValueBySql(String querySql);
}