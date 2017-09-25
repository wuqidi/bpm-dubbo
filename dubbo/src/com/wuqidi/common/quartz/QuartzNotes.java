package com.wuqidi.common.quartz;


import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.securityframework.support.JedisPoolManager;


public class QuartzNotes {
	
	/*@Resource
	private NotesMapper notesMapper;*/
	//@Autowired
	//private RedisTemplate<String,Object> redisTemplate;
	
	@Autowired
	private JedisPoolManager jedisPoolManager;
	
	/**
	 * 同步redis的缓存数据导数据库
	 */
	@SuppressWarnings("deprecation")
	public void execute() {
		JedisPool jedisPool = jedisPoolManager.getJedisPool();
		Jedis jedis = null;
		try {
			/*jedis = jedisPool.getResource();
			int i = RandomUtils.nextInt(0, 100);
			jedis.hset("test","test"+i,"test"+i+"test");
			System.out.println("test"+"test"+i+"test"+i+"test");*/
			//List<Notes> list = notesService.selectList();
			/*List<Notes> list = notesMapper.selectAll();
			
			
			for(Notes noteTem : list){
				jedis.hset(
						Notes.entityName.getBytes(), 
						noteTem.getId().getBytes(),
						SerializationUtils.serialize(noteTem));
			}*/
			
			//Map<byte[], byte[]> mget = jedis.hgetAll(Notes.entityName.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedisPool.returnBrokenResource(jedis);
		}

	}
}
