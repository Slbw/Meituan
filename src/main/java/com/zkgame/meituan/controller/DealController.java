package com.zkgame.meituan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zkgame.meituan.model.City;
import com.zkgame.meituan.model.Deal;
import com.zkgame.meituan.model.MDeal;
import com.zkgame.meituan.model.Shop;
import com.zkgame.meituan.service.ICityService;
import com.zkgame.meituan.service.IDealService;
import com.zkgame.meituan.service.IShopService;
import com.zkgame.meituan.util.DownloadUtil;
import com.zkgame.meituan.util.ProcessUtil;
import com.zkgame.meituan.util.XLog;
import com.zkgame.meituan.util.parse.CityDataParseUtil;

/**
 * 商品信息的控制器
 * 
 * @author DKSlbw
 * 
 */
@Controller
@RequestMapping("/deal")
public class DealController extends BaseController {

	@Autowired
	private IDealService dealService;

	@Autowired
	private IShopService shopService;

	@Autowired
	private ICityService cityService;

	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object list(@RequestParam(value = "cityId", required = false) String cityId,
			@RequestParam(value = "dealCate", required = false) String dealCate,
//			@RequestParam(value = "dealSubcate", required = false) String dealSubcate,
			@RequestParam(value = "callback", required = false) String callback, @RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", cityId.equals("")?null:cityId);
		params.put("dealCateId", dealCate.equals("")?null:dealCate);
//		params.put("dealSubcate", dealSubcate.equals("")?null:dealSubcate);

		if (pageNumber != null && pageSize != null) {
			pageNumber = (pageNumber - 1) * pageSize;

			params.put("startRow", pageNumber);
			params.put("pageSize", pageSize);
		}

		List<Deal> deals = dealService.searchByParams(params);
		int count=dealService.getListCount(params);
		resMap.put("data", deals);
		resMap.put("count", count);
		addResultInfo(resMap, 0);
		// js跨域支持
		if (callback != null) {
			JSONPObject jsonpObject = new JSONPObject(callback, resMap);
			return jsonpObject;
		}

		return resMap;
	}
	
	
	@RequestMapping(value = "/cateList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object cateList(@RequestParam(value = "cityId", required = false) String cityId,
			
			@RequestParam(value = "callback", required = false) String callback) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", cityId);
		List<Map<String, Object>> deals = dealService.getCateList(params);
		resMap.put("data", deals);
		addResultInfo(resMap, 0);
		// js跨域支持
		if (callback != null) {
			JSONPObject jsonpObject = new JSONPObject(callback, resMap);
			return jsonpObject;
		}

		return resMap;
	}
	

	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object search(@RequestParam(value = "cityId", required = false) String cityId,
			@RequestParam(value = "dealTitle", required = false) String dealTitle,
			@RequestParam(value = "dealName", required = false) String dealName, @RequestParam(value = "dealCate", required = false) String dealCate,
			@RequestParam(value = "dealSubcate", required = false) String dealSubcate,
			@RequestParam(value = "callback", required = false) String callback, @RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", cityId.equals("")?null:cityId);
		params.put("dealCate", dealCate.equals("")?null:dealCate);
		params.put("dealSubcate", dealSubcate.equals("")?null:dealSubcate);
		params.put("dealName", dealName.equals("")?null:dealName);
		params.put("dealTitle", dealTitle.equals("")?null:dealTitle);

		if (pageNumber != null && pageSize != null) {
			pageNumber = (pageNumber - 1) * pageSize;

			params.put("startRow", pageNumber);
			params.put("pageSize", pageSize);
		}

		List<Deal> deals = dealService.searchDeal(params);
		resMap.put("data", deals);
		addResultInfo(resMap, 0);

		// js跨域支持
		if (callback != null) {
			JSONPObject jsonpObject = new JSONPObject(callback, resMap);
			return jsonpObject;
		}

		return resMap;
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object detail(@RequestParam(value = "dealId") String dealId, @RequestParam(value = "callback", required = false) String callback) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dealId", dealId);
		List<Deal> deals = dealService.searchByParams(params);
		if (deals.size() > 0) {
			Deal deal = deals.get(0);
			String shopString = deal.getShops().substring(0, deal.getShops().length() - 1);
			String[] shopStringList = shopString.split(",");
			List<Shop> shopList = new ArrayList<Shop>();
			for (int i = 0; i < shopStringList.length; i++) {
				params.clear();
				params.put("shopPoiid", shopStringList[i]);
				List<Shop> shops = shopService.searchByParams(params);
				if (shops.size() > 0) {
					shopList.add(shops.get(0));
				}
			}
			deal.setShopList(shopList);
			resMap.put("data", deals);
			addResultInfo(resMap, 0);

			// js跨域支持
			if (callback != null) {
				JSONPObject jsonpObject = new JSONPObject(callback, resMap);
				return jsonpObject;
			}

		} else {
			addResultInfo(resMap, 80004);// 商品信息不存在
			// js跨域支持
			if (callback != null) {
				JSONPObject jsonpObject = new JSONPObject(callback, resMap);
				return jsonpObject;
			}
		}
		return resMap;
	}

	/**
	 * 更新数据库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object update(HttpServletRequest request, @RequestParam(value = "cityId") String cityId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (cityId == null) {
			addResultInfo(resMap, 0);
			return resMap;
		}

		HttpSession session = request.getSession();
//		session.setAttribute("hello", "world");
//		System.out.println(session.getAttribute("hello"));

		 String path = "/home/data/meituan/"+cityId+".xml";
//		String path = "D:/MeiTuanData/datas/" + cityId + ".xml";
		String url = "http://www.meituan.com/api/v2/" + cityId + "/deals";
		String cityName = "";
		int version = 1;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("divisionId", cityId);
		List<City> cities = cityService.searchByParams(params);
		if (cities.size() == 0)// 城市不存在
		{
			addResultInfo(resMap, 80003);
			return resMap;
		} else {
			cityName = cities.get(0).getDivisionName();
		}
		 File f = new File(path);
//		 isDownloaded=DownloadUtil.httpDownload(url,path,cityId,request.getSession());
		 if(!f.exists())
		 {
		 addResultInfo(resMap, 60001);
		 return resMap;
		 }
		try {
			InputStream inputStream = new FileInputStream(path);
			CityDataParseUtil cityDataParseUtil = new CityDataParseUtil();
			List<MDeal> mDeals = cityDataParseUtil.getDeals(inputStream, version, cityId, request.getSession(true));
			XLog.e("-----数据读取完毕-----");
			params.clear();
			params.put("cityId", cityId);
			List<Deal> deals = dealService.searchByParams(params);
			if (deals.size() > 0)// 如果城市数据不为空
			{
				dealService.removeByCityId(cityId);// 删除对应城市的数据
			}
			int count = 0;
			for (MDeal mdeal : mDeals)// 遍历插入数据库
			{
				String shopListString = "";// 本团购所参与的商店，以逗号分隔
				List<Shop> shops = mdeal.getShops();
				Deal deal = mdeal.getDeal();
				for (Shop shop : shops)// 遍历商店
				{
					if (shop.getShopCity().equals(cityName)) {
						shopListString += shop.getShopPoiid() + ",";
						shopService.insertOrUpdateShop(shop);
					}
				}
				deal.setShops(shopListString);
				deal.setCityId(cityId);
				deal.setCityName(cityName);// 设置城市 过滤掉无用信息
				dealService.saveSelective(deal);
				count++;
				if (count % 53 == 0)// 控制设置频率
				{
					ProcessUtil.newInstance(request.getSession()).setProcess(cityId, count + "." + mdeal.getDeal().getDealName(), "[数据入库]");
				}
				// System.out.println(">>> " + count + "." +
				// mdeal.getDeal().getDealName());
			}
			XLog.e("-----数据写入完毕-----");
		} catch (Exception e) {

			e.printStackTrace();
		}
		addResultInfo(resMap, 0);
		return resMap;
	}
}
