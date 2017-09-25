package com.wuqidi.common.util;

import java.util.Properties;

public class ConfigUtil {
	private static Properties pro = null;
	private static Properties getProperties(String path) {
		if(pro==null){
			pro = ConfigReader.getProperties(path);
		}
		return pro;
	}
	public static Properties configProperties() {
		return getProperties("config.properties");
	}
	public static String getPropertie(String key) {
		return getProperties("config.properties").get(key).toString().trim();
	}
}