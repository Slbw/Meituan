package com.zkgame.meituan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkgame.meituan.dao.maper.${modelName}Mapper;
import com.zkgame.meituan.model.${modelName};
import com.zkgame.meituan.service.I${modelName}Service;

@Service("${serviceName}Service")
public class ${modelName}ServiceImpl extends ServiceImpl<${modelName}, Integer> implements I${modelName}Service {

	@Autowired
	protected ${modelName}Mapper ${serviceName}Mapper;
}