package com.wuqidi.common.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

public final class JSONUtil {
	private static final Logger logger = Logger.getLogger(JSONUtil.class);
	
	/**
	 * 方法功能：将对象转换成JSON字符串，并响应回前台
	 * 参数：object
	 * 返回值：void
	 * 异常：IOException
	 */
	public static String parse(Object object) {
		try {
			return JSON.toJSONString(object);
		} catch (Exception e) {
			logger.error("Object转JSON错误！");
		}
		return null;
	}

}
