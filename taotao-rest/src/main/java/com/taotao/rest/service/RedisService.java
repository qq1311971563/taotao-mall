package com.taotao.rest.service;

import com.taotao.common.entity.WebResult;

public interface RedisService {
	WebResult syncContent(String contentId);
}
