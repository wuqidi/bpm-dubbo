package com.wuqidi.common.util;

public class ExceptionCodeUtil {
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	public static final String IOCE_AU000 = "IOCE: AU000 \r\n    未知的系统异常!";; 
	////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////
	/**未知业务处理异常*/
	public static String IOCE_AS000 = "IOCE: AS000 \r\n    未知业务处理异常！";
	/**查询已执行并返回数据，但数据解析异常*/
	public static String IOCE_AS001 = "IOCE: AS001 \r\n    业务逻辑处理异常！";
	/**参数为空异常*/
	public static String IOCE_AS002 = "IOCE: AS002 \r\n    参数为空异常！";
	/**编码重复异常*/
	public static String IOCE_AS100 = "IOCE: AS100 \r\n    重复的编码异常！";
	/**导入数据格式错误*/
	public static String IOCE_AS900 = "IOCE: AS900 \r\n    导入了错误的数据格式的数据!";
	/**接口测试错误*/
	public static String IOCE_AS930 = "IOCE: AS930 \r\n    接口请求地址错误或接口服务未启动!";
	////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////
	/**未知数据库操作异常*/
	public static final String IOCE_AD000 = "IOCE: AD000 \r\n    未知数据库操作异常!"; 
	/**无法连接数据库，请联系系统管理员*/
	public static final String IOCE_AD001 = "IOCE: AD001 \r\n    无法连接数据库，请联系系统管理员"; 
	/**释放数据库资源异常*/
	public static final String IOCE_AD002 = "IOCE: AD002 \r\n    释放数据库资源异常!";
	/** 分页参数为空或参数名称异常 */
	public static final String IOCE_AD003 = "IOCE: AD003 \r\n    分页参数为空或参数名称异常！";
	/** SQL异常 */
	public static final String IOCE_AD004 = "IOCE: AD003 \r\n    SQL异常";
}
