 package com.zkgame.meituan.util;

import java.util.ResourceBundle;

/**
 * @author Slbw
 *
 */
public class ResultCodeUtil {

	private ResourceBundle rb;

	private ResultCodeUtil() {
		rb = ResourceBundle.getBundle("resultcode");
	}

	private static ResultCodeUtil util;

	public static ResultCodeUtil sharedConfigUtil() {
		if (util == null) {
			util = new ResultCodeUtil();
		}
		return util;
	}

	public String getCodeInfo(int key) {

		String result = rb.getString(String.valueOf(key));
		return result;

	}

}