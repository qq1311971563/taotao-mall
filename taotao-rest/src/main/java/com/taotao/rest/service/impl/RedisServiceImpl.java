package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.WebResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public WebResult syncContent(String contentId) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentId);
		} catch (Exception e) {
			e.printStackTrace();
			return WebResult.build(500, e.getMessage());
		}
		return WebResult.ok();
	}

}
