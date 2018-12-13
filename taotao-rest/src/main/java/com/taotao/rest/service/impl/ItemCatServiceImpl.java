package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.entity.TbItemCat;
import com.taotao.entity.TbItemCatExample;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_ITEM_CAT_KEY}")
	private String INDEX_ITEM_CAT_KEY;

	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getCatList((long) 0));
		return catResult;
	}

	private List<?> getCatList(Long parentId) {
		// 取缓存
		String result = jedisClient.hget(INDEX_ITEM_CAT_KEY, parentId + "");
		if (StringUtils.isNotBlank(result)) {
			return JsonUtils.jsonToList(result, CatNode.class);
		}
		TbItemCatExample example = new TbItemCatExample();
		com.taotao.entity.TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<Object> resultList = new ArrayList<>();
		int count = 1;
		for (TbItemCat tbItemCat : list) {
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId());
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				if (count > 14) {
					break;
				}
			} else {
				resultList.add("/products/" + tbItemCat.getId() + "|" + tbItemCat.getName());
			}
		}

		// 添加缓存
		try {
			jedisClient.hset(INDEX_ITEM_CAT_KEY, parentId + "", JsonUtils.objectToJson(resultList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
