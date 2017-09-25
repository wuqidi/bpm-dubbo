package com.wuqidi.common.cache;

import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.securityframework.support.Constants;
import com.securityframework.support.JedisPoolManager;
import com.securityframework.support.SerializationUtils;

/**
 * @author Administrator
 */
public class DistributedCache implements DeploymentCache<ProcessDefinitionEntity> {
	
	private Logger logger = LoggerFactory.getLogger(DistributedCache.class);

	private JedisPoolManager jedisPoolManager;

	public void setJedisPoolManager(JedisPoolManager jedisPoolManager) {
		this.jedisPoolManager = jedisPoolManager;
	}
	
	
	public Jedis getJedits(){
		return jedisPoolManager.getJedisPool().getResource();
	}
	
	public JedisPool getResources(){
		return jedisPoolManager.getJedisPool();
	}

	@Override
	public ProcessDefinitionEntity get(String id) {
		logger.error("从redis获取数据["+Constants.REDIS_ACT_PRE+id+"]");
		byte[] entityBt = null;
		JedisPool resources = getResources();
		Jedis jedits = null;
		try {
			resources = getResources();
			jedits = getJedits();
			entityBt = jedits.hget(Constants.REDIS_ACT_PRE.getBytes(), id.getBytes());
			if(entityBt==null)return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从redis获取数据失败["+Constants.REDIS_ACT_PRE+id+"]-->"+e.toString());
		}finally{
			if (resources != null) {
				resources.returnBrokenResource(jedits);
			}
		}
		return (ProcessDefinitionEntity)SerializationUtils.deserialize(entityBt);
	}

	@Override
	public void add(String id, ProcessDefinitionEntity processDefinitionEntity) {
		logger.error("向redis存储数据["+Constants.REDIS_ACT_PRE+id+"]");
		JedisPool resources = getResources();
		Jedis jedits = null;
		try {
			resources = getResources();
			jedits = getJedits();
			jedits.hset(Constants.REDIS_ACT_PRE.getBytes(), id.getBytes(),SerializationUtils.serialize(processDefinitionEntity));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("向redis存储数据失败["+Constants.REDIS_ACT_PRE+id+"]-->"+e.toString());
		}finally{
			if (resources != null) {
				resources.returnBrokenResource(jedits);
			}
		}
	}

	@Override
	public void remove(String id) {
		logger.error("从redis删除数据["+Constants.REDIS_ACT_PRE+id+"]");
		JedisPool resources = getResources();
		Jedis jedits = null;
		try {
			resources = getResources();
			jedits = getJedits();
			jedits.hdel(Constants.REDIS_ACT_PRE.getBytes(), id.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从redis删除数据失败["+Constants.REDIS_ACT_PRE+id+"]-->"+e.toString());
		}finally{
			if (resources != null) {
				resources.returnBrokenResource(jedits);
			}
		}
	}

	@Override
	public void clear() {
		logger.error("从redis删除全部流程数据["+Constants.REDIS_ACT_PRE +"]");
		JedisPool resources = getResources();
		Jedis jedits = null;
		try {
			resources = getResources();
			jedits = getJedits();
			jedits.hdel(Constants.REDIS_ACT_PRE.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从redis删除全部流程失败["+Constants.REDIS_ACT_PRE +"]-->"+e.toString());
		}finally{
			if (resources != null) {
				resources.returnBrokenResource(jedits);
			}
		}
	}

}