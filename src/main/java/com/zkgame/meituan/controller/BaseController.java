package com.zkgame.meituan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zkgame.meituan.util.ResultCodeUtil;

/**
 * @author Slbw
 *
 */
public class BaseController {
	
	
	private static String basePath;
	
//	@Autowired
	private HttpServletRequest request;
	
	protected String getBasePath() {
		if(request == null) {
			basePath = "http://localhost:8080/Meituan";
		}
		
		if(basePath == null) {
			String path = request.getContextPath();
			basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		}
		return basePath;
	}
	
	/**
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 参数检查
	 * 
	 * @param value 参数值
	 * @param mess 参数说明
	 * @return
	 */
	protected boolean validParam(Object value, String mess) {
		if(StringUtils.isEmpty(value)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 添加返回码和返回信息
	 */
	protected void addResultInfo(Map<String, Object> map,int code)
	{
		map.put("ret", code);
		map.put("msg", ResultCodeUtil.sharedConfigUtil().getCodeInfo(code));
	}
}

