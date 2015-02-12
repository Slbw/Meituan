/**
 * @Project Meituan
 * @Name CityController
 * @User Slbw
 * @Time 2015-1-21 下午4:46:58
 * @Version 1.0
 * @describe 
 */
package com.zkgame.meituan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.zkgame.meituan.model.City;
import com.zkgame.meituan.model.UpdateRecord;
import com.zkgame.meituan.service.ICityService;
import com.zkgame.meituan.service.IUpdateRecordService;
import com.zkgame.meituan.util.XLog;
import com.zkgame.meituan.util.parse.CityListParseUtil;

/**
 * @author F
 * 
 */
@Controller
@RequestMapping("/city")
public class CityController extends BaseController {

	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IUpdateRecordService updateRecordService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Object list(@RequestParam(value = "callback" ,required=false) String callback) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String,Object> params=new HashMap<String,Object>();
		List<City> cities = cityService.searchByParams(params);
		for(City city:cities)
		{
			city.setLocationTimezone(null);
			city.setLocationTimezoneOffsetGmt(null);
		}
		resMap.put("data", cities);
		addResultInfo(resMap, 0);
		//js跨域支持
		if(callback!=null)
		{
			JSONPObject jsonpObject=new JSONPObject(callback, resMap);
			return jsonpObject;
		}
		return resMap;
	}

	/**
	 * 更新数据库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Object update(HttpServletRequest request) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		URL url = null;
		try {
			url = new URL("http://www.meituan.com/api/v1/divisions");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			CityListParseUtil saxParseUtil = new CityListParseUtil();
			List<City> cities = saxParseUtil.getCities(inStream);
			Map<String, Object> params = new HashMap<String, Object>();
			if (cityService.searchByParams(params).size() != 0)// 有数据的话清空表
				cityService.resetTable();
			for (City city : cities) {
				cityService.saveSelective(city);
			}
			XLog.d("-----城市更新完毕-----");
			for (City city : cities) {
				UpdateRecord record=new UpdateRecord();
				record.setCityId(city.getDivisionId());
				record.setCityName(city.getDivisionName());
				record.setVersion(0);
				record.setLastUpdate((int)(System.currentTimeMillis()/1000));
				updateRecordService.initRecord(record);
			}
			XLog.d("-----初始化更新记录完毕-----");
		} catch (Exception e) {

			e.printStackTrace();
		}
		addResultInfo(resMap, 0);
		return resMap;
	}
}
