package com.zkgame.meituan.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Slbw
 *
 */
public class SignUtil {
	
	private static SignUtil sign;
	
	public static final String APP_KEY="22ff9b257f19883147482c9aa1411e19";
	public static final String APP_SECRET="fc9dc5edd499e49580c0f77258460789";
	
	public static final SignUtil SharedSignUtil() {
		if (sign == null) {
			try {
				sign = new SignUtil();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sign;
	}
	
	/**
	 * 用户生成签名的合法性验证
	 * @param sign 签名
	 * @param param 签名需要的额外参数列表
	 * @param isRegister 是否为注册接口
	 * @return 是否合法
	 */
	public boolean isAuthorized(String sign,HashMap<String,String> param)
	{
		if(sign.equals(getSequenceString(param)))
		return true;
		return false;
	}
	
	/**
	 * 获取被排序后的被加密的sign
	 * @param param
	 * @return
	 */
	private String getSequenceString(HashMap<String,String> param)
	{
		//拼接appKey 和 appSecret
		param.put("app_secret", APP_SECRET);
		String str="";
		 Collection<String> keyset= param.keySet();
	     List<String> list = new ArrayList<String>(keyset);
	     //对key键值按字典升序排序
	     Collections.sort(list);
	     for(int i=0;i<list.size();i++)
	     {
	    	 if(param.get(list.get(i))==null)
	    		 continue;
	    	 str+=list.get(i)+param.get(list.get(i));
	     }
	   String  result=MD5Util.SharedMD5Util().Md5(str);
		return result;
	}
	
	
	/**
	 * 获取sign字符串
	 */
	public String getSign(HashMap<String,String> param)
	{
		return getSequenceString(param);
	}

}
