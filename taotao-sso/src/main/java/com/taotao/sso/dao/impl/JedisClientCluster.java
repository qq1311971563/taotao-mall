package com.taotao.sso.dao.impl;

import com.taotao.sso.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {
	@Autowired
	private JedisCluster cluster;

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		return cluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		return cluster.hget(hkey, key);
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		return cluster.hset(hkey, key, value);
	}

	@Override
	public Long incr(String key) {
		return cluster.incr(key);
	}

	@Override
	public Long expire(String key, Integer second) {
		return cluster.expire(key, second);
	}

	@Override
	public Long ttl(String key) {
		return cluster.ttl(key);
	}

	@Override
	public Long del(String key) {
		return cluster.del(key);
	}

	@Override
	public Long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return cluster.hdel(hkey, key);
	}

}
