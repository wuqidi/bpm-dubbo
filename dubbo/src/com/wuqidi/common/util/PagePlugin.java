package com.wuqidi.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PagePlugin implements Interceptor {
	protected Logger logger = Logger.getLogger(PagePlugin.class);
	private static String dialect = "";	//数据库方言
	private static String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)
	
	public Object intercept(Invocation ivk) throws Throwable {
		HashMap<String, Object> map = null;
		String sql = null;
		if(ivk.getTarget() instanceof RoutingStatementHandler){
			RoutingStatementHandler statementHandler = (RoutingStatementHandler)ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
			
			if(mappedStatement.getId().contains(pageSqlId)){ //拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if(parameterObject==null){
					throw new RuntimeException("分页参数未初始化！");
				}else{
					Connection connection = (Connection) ivk.getArgs()[0];
					String orderBy = null;
					if(parameterObject instanceof HashMap){
						map = (HashMap<String, Object>)parameterObject;
						map.put("pagination", true);
					}
					BoundSql boundSql2 = mappedStatement.getBoundSql(parameterObject);
					String countSql = boundSql2.getSql();
					int len = countSql.lastIndexOf("ORDER BY");
					if(len==-1){
						len = countSql.lastIndexOf("order by");
					}
					if(len>0){
						countSql = countSql.substring(0, len);
					}
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObject);
					setParameters(countStmt,mappedStatement,countBS,map);
					ResultSet rs = null;
					try {
						rs = countStmt.executeQuery();
					} catch (Exception e) {
						logger.error(new RuntimeException(e) + "\r\n" + countSql + "\r\n访问参数：" + JSON.toJSONString(map));
						throw e;
					}
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					if(parameterObject instanceof HashMap){	//参数就是Page实体
						 map = (HashMap<String, Object>) parameterObject;
						 map.put("pagination", false);
						 map.put("totalRows",""+count);
					}
					sql = generatePageSql(boundSql.getSql(),map);
					ReflectHelper.setValueByFieldName(boundSql, "sql", sql); //将分页sql语句反射回BoundSql.
				}
			}
		}
		try {
			return ivk.proceed();
		} catch (Exception e) {
			logger.error(new RuntimeException(e) + "\r\n" + sql + "\r\n访问参数：" + JSON.toJSONString(map));
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
					}
//					if("orderBy".equals(propertyName)){
//						i--;
//					}else{
						typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
//					}
				}
			}
		}
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql,HashMap<String, Object> map) throws Exception {
		sql += map.get("orderBy")!=null?" ORDER BY "+(String) map.get("orderBy"):"";
		if(map!=null && !StringUtils.isEmpty(dialect)){
			StringBuffer pageSql = new StringBuffer();
			if("mysql".equals(dialect)){
				pageSql.append(sql);
				try {
					if( map.get("findAll")!=null && (Boolean)map.get("findAll")){
						
					}else{
						try {
							pageSql.append(" limit "+getPageStartIndex(map)+","+map.get("pageSize"));
						} catch (Exception e) {
							throw e;
						}
					}
				} catch (Exception e) {
					logger.error(ExceptionCodeUtil.IOCE_AD003 + "\r\n" + sql + "\r\n访问参数：" + JSON.toJSONString(map));
					throw new RuntimeException(ExceptionCodeUtil.IOCE_AD003,e);
					//查询全部
				}
				
			}else if("oracle".equals(dialect)){
			}
			return pageSql.toString();
		}else{
			return sql;
		}
	}
	
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (StringUtils.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Integer getPageStartIndex(HashMap<String, Object> map) {
		Integer currentPage = Integer.parseInt((String)map.get("currentPage"));
		Integer pageSize = Integer.parseInt((String)map.get("pageSize"));
		return (currentPage-1)*pageSize; 
	}
	
}
