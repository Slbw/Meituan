package com.zkgame.meituan.service.codeGenerator;

import java.util.Map;

import freemarker.template.Configuration;

public interface ICodeGenerator {
	void excute(Configuration conf, Map<String, Object> clazz) throws Exception;
}
