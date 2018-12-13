package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.WebResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.entity.TbContent;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		try {
			WebResult webResult = WebResult.formatToList(result, TbContent.class);
			@SuppressWarnings("unchecked")
			List<TbContent> list = (List<TbContent>) webResult.getData();
			List<Map<String, Object>> resultList = new ArrayList<>();
			for (TbContent tbContent : list) {
				Map<String, Object> map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", tbContent.getPic());
				map.put("width", tbContent.getPic());
				map.put("srcB", tbContent.getPic());
				map.put("heightB", tbContent.getPic());
				map.put("widthB", tbContent.getPic());
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
