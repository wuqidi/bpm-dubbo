package com.wuqidi.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigReader {
	public static Properties getProperties(String propertiesName) {
		try {
			ClassLoader classLoader = getDefaultClassLoader();
			InputStream is = classLoader.getResourceAsStream(propertiesName);
			if (null == is) {
				classLoader = ConfigReader.class.getClassLoader();
				is = classLoader.getResourceAsStream(propertiesName);
				if (null == is) {
					return null;
				}
			}
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			Properties prop = new Properties();
			prop.load(bf);
			return prop;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable localThrowable) {
		}
		if (cl == null) {
			cl = ConfigReader.class.getClassLoader();
		}
		return cl;
	}

	public static String getPropertiesValue(String propertiesName, String key) {
		Properties prop = getProperties(propertiesName);
		if (null != prop) {
			if (prop.containsKey(key)) {
				return prop.getProperty(key);
			}
		}
		return "";
	}
}