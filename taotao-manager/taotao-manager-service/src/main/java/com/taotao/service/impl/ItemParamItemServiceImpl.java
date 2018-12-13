package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.entity.WebResult;
import com.taotao.entity.TbItemParamItem;
import com.taotao.entity.TbItemParamItemExample;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public WebResult insertItemParamItem(Long itemId, String itemParam) {
		TbItemParamItem record = new TbItemParamItem();
		record.setItemId(itemId);
		record.setParamData(itemParam);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		itemParamItemMapper.insert(record);
		return WebResult.ok();
	}

	@Override
	public WebResult getItemParamItem(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.entity.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return WebResult.ok(list.get(0));
		}
		return WebResult.ok();
	}

}
