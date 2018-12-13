package com.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {
	@Autowired
	private JedisPool pool;

	@Override
	public String get(String key) {
		Jedis jedis = pool.getResource();
		return jedis.get(key);
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = pool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String hkey, String key) {

		Jedis jedis = pool.getResource();
		String result = jedis.hget(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = pool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, Integer second) {
		Jedis jedis = pool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Jedis jedis = pool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

}
