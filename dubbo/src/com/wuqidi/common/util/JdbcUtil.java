package com.wuqidi.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	  
	/** 
	 * JDBC 的工具类 
	 *  
	 * 其中包含: 获取数据库连接, 关闭数据库资源等方法. 
	 */  
	public class JdbcUtil {  
	  
	    public static Connection getConnection() throws Exception {  
	        // 1. 准备获取连接的 4 个字符串: username, password, url, driver  
	        String user = ConfigUtil.getPropertie("jdbc.username");  
	        String password = ConfigUtil.getPropertie("jdbc.password");  
	        String url= ConfigUtil.getPropertie("jdbc.url");  
	        String jdbcDriver= ConfigUtil.getPropertie("jdbc.driver");  
	  
	        // 2. 加载驱动: Class.forName(driverClass)  
	        Class.forName(jdbcDriver);  
	  
	        // 3.获取数据库连接  
	        Connection connection = DriverManager.getConnection(url, user,  
	                password);  
	        return connection;  
	    }
	    
	    public static PreparedStatement getPreparedStatement(String sql) throws SQLException, Exception{
	    	return getConnection().prepareStatement(sql);
	    }
	  
	    public static void close(ResultSet resultSet, Statement statement,  
	            Connection connection) {  
	  
	        if (resultSet != null) {  
	            try {  
	                resultSet.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	  
	        if (statement != null) {  
	            try {  
	                statement.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	  
	        if (connection != null) {  
	            try {  
	                connection.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    
		public static String getUrl(String dbType, String ip, String port,
				String dbName) {
			switch(dbType.toLowerCase()){
				case "oracle":
					return "jdbc:oracle:thin:@"+ip+":"+port+":"+dbName;
				default:
					return "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=true";
			}
		}

		public static String getDriver(String dbType) {
			switch(dbType.toLowerCase()){
				case "oracle":
					return "oracle.jdbc.driver.OracleDriver";
				default:
					return "com.mysql.jdbc.Driver";
			}
		}
	  
	}  

