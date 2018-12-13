package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.entity.TbContent;
import com.taotao.entity.TbContentExample;
import com.taotao.mapper.TbContentMapper;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(Long id) {
		// 取缓存
		try {
			String cashString = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, id + "");
			if (!StringUtils.isBlank(cashString)) {
				return JsonUtils.jsonToList(cashString, TbContent.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 缓存中没有，在数据库进行查询
		TbContentExample example = new TbContentExample();
		com.taotao.entity.TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		List<TbContent> list = contentMapper.selectByExample(example);
		// 写缓存
		try {
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, id + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
