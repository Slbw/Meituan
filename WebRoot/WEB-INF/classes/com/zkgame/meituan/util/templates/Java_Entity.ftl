package com.zkgame.meituan.model;

import java.util.Date;

public class ${modelName} extends BaseBean {
	<#list fields as field>
	/**
	 * ${field.remark}
	 */
	private ${field.propertyType} ${field.propertyName};
	
	</#list>
	
	<#list fields as field>
	public void set${field.propertyNameGS}(${field.propertyType} ${field.propertyName}) {
		this.${field.propertyName} = ${field.propertyName};
	}
	
	public ${field.propertyType} get${field.propertyNameGS} () {
		return ${field.propertyName};
	}
	
	</#list>
}