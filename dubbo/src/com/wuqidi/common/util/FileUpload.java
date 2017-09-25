package com.wuqidi.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	public static String upload(MultipartFile file, String filePath, String fileName){
		try {
			copyFile(file.getInputStream(), filePath, fileName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName;
	}
	
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
			File file = new File(dir, realName);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	/**
	 * 自定义判断图片大小方法
	 */
	public static String imgSize(String filePath){
		String value = "success";
		File file = new File(filePath);
		if(file.length()>1024*1024){
			value = "false";
		}
		return value;
	}
}