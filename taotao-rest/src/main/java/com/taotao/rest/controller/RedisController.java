package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.entity.WebResult;
import com.taotao.rest.service.RedisService;

@Controller
public class RedisController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("/content/{contentCId}")
	@ResponseBody
	public WebResult contentCacheSync(@PathVariable String contentCId) {
		return redisService.syncContent(contentCId);
	}

}
