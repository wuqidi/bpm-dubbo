package com.wuqidi.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
* 全局配置类
* 获取配置文件项、值
* @author 吴启迪
* @date:2016-12-12 下午5:44:04
* @version :
* 
*/
public class ResourceFileUtil {
  
    /**
     * 获取配置文件中项目、值
     * @param fileName 文件路径+名称
     * @param param  获取项
     * @return
     * @throws FileNotFoundException
     */
    public static String getValue(String fileName,String param) throws FileNotFoundException {
    	String param1="";
        Properties prop =loadProperties(fileName);   
        try {   
            param1 = prop.getProperty(param).trim();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
        return param1;
    } 
    /**
     * 加载配置文件
     * @param  resources 文件存放位置
     * @return 返回配置文件  类型： properties
     * @throws FileNotFoundException
     */
	public static Properties loadProperties(String resources)
			throws FileNotFoundException {
		// 使用InputStream得到一个资源文件
		InputStream inputstream = new BufferedInputStream(new FileInputStream(
				resources));
		// new 一个Properties
		Properties properties = new Properties();
		try {
			// 加载配置文件
			properties.load(inputstream);
			return properties;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				inputstream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	/**
	 * 修改配置项的值,如果没有该项会自动创建该项并赋值
	 * @param fileName 文件路径+名称
     * @param param    获取项
	 * @return 1：成功；0：失败
	 * @throws FileNotFoundException 
	 */
	public static int updateValue(String fileName,String param,String value) throws FileNotFoundException{
        Properties prop =loadProperties(fileName);
        int i = 1;
        try {
        	prop.setProperty(param, value);
            prop.store(getFileOutputStream(fileName), "将"+param+"修改为"+value);
        } catch (Exception e) {   
            i = 0;
        }
		return i;
	}
	/**
	 * 获取文件OutputStream流
	 * @param fileName 文件路径+名称
	 * @return OutputStream流
	 */
	public static OutputStream getFileOutputStream(String fileName){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			return out;
		} catch (FileNotFoundException e) {
			System.out.println("写入文件失败："+e.toString()+"类名：ResourceFileUtil");
		} finally{
        	try {
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
        } 
		return null;
	}
}