package com.zkgame.meituan.util;

/**
 * 自定义输出
 * 
 * @author DKSlbw
 * 
 */
public class XLog {
	public static boolean isOn = true;

	public static void d(String d) {
		if (isOn)
			System.out.println(d);
	}

	public static void e(String e) {
		if (isOn)
			System.err.println(e);
	}

}
