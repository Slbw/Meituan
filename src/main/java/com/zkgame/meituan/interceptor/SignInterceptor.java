package com.zkgame.meituan.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zkgame.meituan.util.ResultCodeUtil;
import com.zkgame.meituan.util.SignUtil;

/**
 * @author Slbw
 * 
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		
		// 忽略 login register update 等无需验证的操作
		String requestUrl = request.getRequestURI();
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		String sign = null;// 验证签名

		Map<String, String[]> map = request.getParameterMap();
		// 检查基础参数的完整性
		if (!map.containsKey("sign")) {
			setResult(10006, response);// 缺少sign
			return false;
		}
		if (!map.containsKey("app_key")) {
			setResult(10009, response);// 缺少app_key
			return false;
		}
		if (!map.containsKey("version")) {
			setResult(10008, response);// 缺少version
			return false;
		}
		if (!map.containsKey("timestamp")) {
			setResult(10007, response);// 缺少timestamp
			return false;
		}
		sign = map.get("sign")[0];

		HashMap<String, String> mMap = new HashMap<String, String>();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String val = ((String[]) entry.getValue())[0];
			if (!key.equalsIgnoreCase("sign"))
				mMap.put(key, val);
		}
		// 参数验证
		boolean isAuthorized = SignUtil.SharedSignUtil().isAuthorized(sign, mMap);
		if (!isAuthorized) {
			setResult(10010, response);// 验证失败
			return false;
		}
		return true;
	}

	/**
	 * 获取错误返回
	 * @param code 返回码
	 * @param response
	 * @throws IOException
	 */
	private void setResult(int code, HttpServletResponse response) throws IOException {
		String ret = "{\"ret\":" + code + ",\"msg\":\"" + ResultCodeUtil.sharedConfigUtil().getCodeInfo(code) + "\"}";
		PrintWriter out = response.getWriter();
		out.println(ret);
		out.flush();
		out.close();
	}

}
