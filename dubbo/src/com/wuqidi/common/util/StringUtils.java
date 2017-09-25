package com.wuqidi.common.util;

import java.util.LinkedList;
import java.util.List;

public class StringUtils {

	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s.trim()) || "null".equals(s.trim());
	}

	public static List<String> convStrToList(String notCheckURLListStr, String string) {
		List<String> returList = new LinkedList<String>();
		String[] split = notCheckURLListStr.split(string);
		for(String tem : split){
			returList.add(tem);
		}
		return returList;
	}

}
