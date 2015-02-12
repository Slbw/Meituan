/**
 * @Project Meituan
 * @Name SaxParseUtil
 * @User Slbw
 * @Time 2015-1-23 下午3:46:49
 * @Version 1.0
 * @describe 
 */
package com.zkgame.meituan.util.parse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.zkgame.meituan.model.City;
import com.zkgame.meituan.model.Deal;
import com.zkgame.meituan.model.MDeal;
import com.zkgame.meituan.model.Shop;
import com.zkgame.meituan.util.ProcessUtil;

public class CityDataParseUtil extends DefaultHandler {
	private List<MDeal> deals = null;
	private List<Shop> shops = null;
	private MDeal Mdeal = null;
	private Shop shop = null;
	private Deal deal = null;
	private int count=0;
	private int version=1;
	private String preTag = null;// 作用是记录解析时的上一个节点名称
	private HttpSession session;
	private String cityId;

	public List<MDeal> getDeals(InputStream xmlStream,int version,String cityId,HttpSession session) throws Exception {
//		this.version=version;
//		this.cityId=cityId;
//		this.session=session;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		CityDataParseUtil handler = new CityDataParseUtil();
		handler.version=version;
		handler.cityId=cityId;
		handler.session=session;
		parser.parse(xmlStream, handler);
		return handler.getDeals();
	}

	public List<MDeal> getDeals() {
		return deals;
	}

	@Override
	public void startDocument() throws SAXException { 
		deals = new ArrayList<MDeal>();
		shops = new ArrayList<Shop>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("data".equals(qName)) {
			count++;//
			Mdeal = new MDeal();
		} else if ("deal".equals(qName)) {
			deal = new Deal();
		} else if ("shops".equals(qName)) {
			shops=new ArrayList<Shop>();// 清空商店标签
		} else if ("shop".equals(qName)) {
			shop = new Shop();
		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("data".equals(qName)) {
			Mdeal.setDeal(deal);
			Mdeal.setShops(shops);
			deals.add(Mdeal);
			Mdeal = null;
		} else if ("shop".equals(qName)) {
			shop.setShopVersion(version);
			shops.add(shop);// 添加商店
		}
		preTag = null;
		/**
		 * 当解析结束时置为空。这里很重要，例如，当图中画3的位置结束后，会调用这个方法
		 * ，如果这里不把preTag置为null，根据startElement(....)方法，preTag的值还是book，当文档顺序读到图
		 * 中标记4的位置时，会执行characters(char[] ch, int start, int
		 * length)这个方法，而characters(....)方
		 * 法判断preTag!=null，会执行if判断的代码，这样就会把空值赋值给book，这不是我们想要的。
		 */
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);
			if ("website".equals(preTag)) {
				deal.setWebsite(content);
			} else if ("deal_more_img".equals(preTag)) {
				deal.setDealMoreImg(content);
			} else if ("reservation".equals(preTag)) {
				deal.setReservation(Integer.valueOf(content));
			} else if ("destination".equals(preTag)) {
				deal.setDestination(content);
			} else if ("partner".equals(preTag)) {
				deal.setPartner(Integer.valueOf(content));
			}/* else if ("city_name".equals(preTag)) {
				deal.setCityName(content);
			}/* else if ("city_id".equals(preTag)) {
				deal.setCityId(content);
			}*/ else if ("city_url".equals(preTag)) {
				deal.setCityUrl(content);
			} else if ("deal_id".equals(preTag)) {
				deal.setDealId(Integer.valueOf(content));
			} else if ("deal_title".equals(preTag)) {
				deal.setDealTitle(content);
			} else if ("deal_rank".equals(preTag)) {
				deal.setDealRank(Integer.valueOf(content));
			} else if ("deal_url".equals(preTag)) {
				deal.setDealUrl(content);
			} else if ("deal_img".equals(preTag)) {
				deal.setDealImg(content);
			} else if ("deal_cate_id".equals(preTag)) {
				deal.setDealCateId(Integer.valueOf(content));
			} else if ("deal_cate".equals(preTag)) {
				deal.setDealCate(content);
			} else if ("deal_subcate_id".equals(preTag)) {
				deal.setDealSubcateId(Integer.valueOf(content));
			} else if ("deal_subcate".equals(preTag)) {
				deal.setDealSubcate(content);
			} else if ("deal_desc".equals(preTag)) {
				deal.setDealDesc(content);
			} else if ("value".equals(preTag)) {
				deal.setValue(content);
			} else if ("price".equals(preTag)) {
				deal.setPrice(content);
			} else if ("rebate".equals(preTag)) {
				deal.setRebate(content);
			} else if ("refund".equals(preTag)) {
				deal.setRefund(Integer.valueOf(content));
			} else if ("sales_min".equals(preTag)) {
				deal.setSalesMin(Integer.valueOf(content));
			} else if ("sales_num".equals(preTag)) {
				deal.setSalesNum(Integer.valueOf(content));
			} else if ("sold_out".equals(preTag)) {
				deal.setSoldOut(content);
			} else if ("is_post".equals(preTag)) {
				deal.setIsPost(content);
			} else if ("start_time".equals(preTag)) {
				deal.setStartTime(Integer.valueOf(content));
			} else if ("end_time".equals(preTag)) {
				deal.setEndTime(Integer.valueOf(content));
			} else if ("coupon_start_time".equals(preTag)) {
				deal.setCouponStartTime(Integer.valueOf(content));
			} else if ("coupon_end_time".equals(preTag)) {
				deal.setCouponEndTime(Integer.valueOf(content));
			} else if ("deal_tips".equals(preTag)) {
				deal.setDealTips(content);
			} else if ("deal_wow".equals(preTag)) {
				deal.setDealWow(content);
			}/* else if ("deal_range".equals(preTag)) {
				deal.setDealRange(content);
			} else if ("deal_range_id".equals(preTag)) {
				deal.setDealRangeId(content);
			} else if ("deal_district_id".equals(preTag)) {
				deal.setDealDistrictId(content);
			} else if ("deal_district_name".equals(preTag)) {
				deal.setDealDistrictName(content);
			}*/ else if ("deal_address".equals(preTag)) {
				deal.setDealAddress(content);
			} else if ("deal_lng".equals(preTag)) {
				deal.setDealLng(content);
			} else if ("deal_lat".equals(preTag)) {
				deal.setDealLat(content);
			} else if ("deal_name".equals(preTag)) {
//				System.out.println(count+"."+content);
				if(count%53==0)//控制设置频率
				{
					ProcessUtil.newInstance(session).setProcess(cityId,count + "." + content,"[数据入库]");
				}
				deal.setDealName(content);
			} else if ("deal_seller".equals(preTag)) {
				deal.setDealSeller(content);
			}/* else if ("deal_phones".equals(preTag)) {
				deal.setDealPhones(content);
			}*/ else if ("deal_roomtype".equals(preTag)) {
				deal.setDealRoomtype(content);
			} else if ("deal_roomtime".equals(preTag)) {
				deal.setDealRoomtime(content);
			} else if ("coupon".equals(preTag)) {
				deal.setCoupon(Integer.valueOf(content));
			}
			// shop
			else if ("shop_name".equals(preTag)) {
				shop.setShopName(content);
			} else if ("shop_dpid".equals(preTag)) {
				shop.setShopDpid(content);
			} else if ("shop_poiid".equals(preTag)) {
				shop.setShopPoiid(Integer.valueOf(content));
			} else if ("shop_tel".equals(preTag)) {
				shop.setShopTel(content);
			} else if ("shop_addr".equals(preTag)) {
				shop.setShopAddr(content);
			} else if ("shop_area".equals(preTag)) {
				shop.setShopArea(content);
			} else if ("shop_long".equals(preTag)) {
				shop.setShopLong(content);
			} else if ("shop_lat".equals(preTag)) {
				shop.setShopLat(content);
			} else if ("shop_city".equals(preTag)) {
				shop.setShopCity(content);
			} else if ("shop_trafficinfo".equals(preTag)) {
				shop.setShopTrafficinfo(content);
			}
		}
	}

}