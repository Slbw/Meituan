package com.zkgame.meituan.util.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.zkgame.meituan.model.CodeDetailBean;
import com.zkgame.meituan.service.ICodeDetailService;
import com.zkgame.meituan.util.SpringContextUtils;

/**
 * 自定义标基类签类，封装定义属性及主体执行方法
 * 
 */
public abstract class BaseTag extends SimpleTagSupport implements
		DynamicAttributes {
	public static final String ATTR_TEMPLATE = "%s=\"%s\"";

	/** 动态属性定义，如事件、样式等 */
	protected Map<String, Object> tagAttrs = new HashMap<String, Object>();
	private ICodeDetailService codeDetailService;
	/** 一维代码表属性名 */
	protected String codeName;
	/** 自定义查询sql */
	protected String querySql;
	/** 默认值，常用于修改及查看页面中加载选中值 */
	protected String defaultValue;
	protected String name;
	/** 标签id属性，与标签name属性相同，用于表单名称定义，映射至bean中 */
	protected String id;
	/** 是否存在默认选项值，针对于select是否存在默认选项，默认情况下为false，即存在 */
	protected boolean emptyOption;
	/** 输出流 */
	protected JspWriter out;

	/** 加载结果集，针对一维代码情况加载明细项，针对查询sql结果，加载key、value至明细bean */
	protected List<CodeDetailBean> codeResults;
	/** 自定义标签内容 */
	protected StringBuilder htmlContents;

	/**
	 * 动态属性设置
	 */
	@Override
	public void setDynamicAttribute(String uri, String name, Object value)
			throws JspException {
		this.tagAttrs.put(name, value);
	}

	/**
	 * 采用模板方法，定义执行方法骨架，具体执行可有实现类覆写或实现
	 */
	public void doTag() throws JspException, IOException {
		init();
		doFindValue();
		processContents();
		outputContents();
	}

	/**
	 * 初始化，包括流、业务等对象创建
	 */
	public void init() {
		out = getJspContext().getOut();
		if (codeDetailService == null) {
			this.codeDetailService = ((ICodeDetailService) SpringContextUtils
					.getBean("codeDetailService"));
		}
		htmlContents = new StringBuilder();
	}

	/**
	 * 获取值
	 * 
	 * @throws JspException
	 * @throws IOException
	 */
	private void doFindValue() throws JspException, IOException {
		if (StringUtils.isNotEmpty(codeName)) {
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(codeName)) {
				conditionMap.put("codeName", codeName);
			}
			// codeResults = this.codeDetailService.query(conditionMap);
		} else if (StringUtils.isNotEmpty(querySql)) {
			codeResults = codeDetailService.getCodeValueBySql(querySql);
		} else {
			out.print("");
		}
	}

	public abstract void processContents() throws IOException;

	public void outputContents() throws IOException {
		out.print(htmlContents);
	}

	public Map<String, Object> getTagAttrs() {
		return tagAttrs;
	}

	public ICodeDetailService getCodeDetailService() {
		return codeDetailService;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getQuerySql() {
		return querySql;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public boolean isEmptyOption() {
		return emptyOption;
	}

	public JspWriter getOut() {
		return out;
	}

	public void setTagAttrs(Map<String, Object> tagAttrs) {
		this.tagAttrs = tagAttrs;
	}

	public void setCodeDetailService(ICodeDetailService codeDetailService) {
		this.codeDetailService = codeDetailService;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmptyOption(boolean emptyOption) {
		this.emptyOption = emptyOption;
	}

	public void setOut(JspWriter out) {
		this.out = out;
	}

}
