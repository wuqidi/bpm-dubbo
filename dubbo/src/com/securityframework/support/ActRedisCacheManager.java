package com.securityframework.support;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActRedisCacheManager{
	private static final Logger logger = LoggerFactory.getLogger(ActRedisCacheManager.class);  

	private JedisPoolManager redisManager; 
    
    public JedisPoolManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(JedisPoolManager redisManager) {
		this.redisManager = redisManager;
	}
}
