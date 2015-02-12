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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.zkgame.meituan.model.City;

public class CityListParseUtil extends DefaultHandler {
	private List<City> cities = null;
	private City city = null;
	private String preTag = null;// 作用是记录解析时的上一个节点名称

	public List<City> getCities(InputStream xmlStream) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		CityListParseUtil handler = new CityListParseUtil();
		parser.parse(xmlStream, handler);
		return handler.getCities();
	}

	public List<City> getCities() {
		return cities;
	}

	@Override
	public void startDocument() throws SAXException {
		cities = new ArrayList<City>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("division".equals(qName)) {
			city = new City();
		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("division".equals(qName)) {
			cities.add(city);
			city = null;
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
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);
			if ("id".equals(preTag)) {
				city.setDivisionId(content);
			} else if ("name".equals(preTag)) {
				
				city.setDivisionName(content);
			} else if ("timezone".equals(preTag)) {
				city.setLocationTimezone(content);
			} else if ("timezone_offset_gmt".equals(preTag)) {
				city.setLocationTimezoneOffsetGmt(content);
			} else if ("latitude".equals(preTag)) {
				city.setLocationLatitude(content);
			} else if ("longitude".equals(preTag)) {
				city.setLocationLongitude(content);
			}
		}
	}

}